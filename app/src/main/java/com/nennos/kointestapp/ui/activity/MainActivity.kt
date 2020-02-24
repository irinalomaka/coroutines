package com.nennos.kointestapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.nennos.kointestapp.R
import com.nennos.kointestapp.network.Api
import com.nennos.kointestapp.network.models.UserNetwork
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.navHostFragment)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(setOf(R.id.mainFragment))
        )
    }


    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
