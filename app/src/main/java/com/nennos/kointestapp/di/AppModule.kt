package com.nennos.kointestapp.di

import android.app.Application
import androidx.room.Room
import com.nennos.kointestapp.db.AppDatabase
import com.nennos.kointestapp.db.dao.UserDao
import com.nennos.kointestapp.network.Api
import com.nennos.kointestapp.repositoty.UserLocalRepository
import com.nennos.kointestapp.repositoty.UserLocalRepositoryImpl
import com.nennos.kointestapp.repositoty.UserNetworkRepository
import com.nennos.kointestapp.repositoty.UserNetworkRepositoryImpl
import com.nennos.kointestapp.ui.fragment.MainFragmentViewModel
import com.nennos.kointestapp.ui.fragment.UserViewModel
import com.nennos.kointestapp.utils.Const.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {

    factory { UserNetworkRepositoryImpl(get()) as UserNetworkRepository }

    factory { UserLocalRepositoryImpl(get()) as UserLocalRepository }
}


val viewModule = module {

    viewModel { MainFragmentViewModel(get(), get()) }

    viewModel { UserViewModel(get()) }
}

val dbModule = module {

    fun provideDatabase(application: Application) = AppDatabase.invoke(application)

    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    single { provideDatabase(androidApplication()) }

    single { provideUserDao(get()) }
}


val apiModule = module {

    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.writeTimeout(10, TimeUnit.SECONDS)
        okHttpClient.readTimeout(10, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(interceptor)
        return okHttpClient.build()
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    single { provideApi(get()) }

    single { provideHttpClient() }

    single { provideRetrofit(get()) }
}
