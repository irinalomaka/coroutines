package com.nennos.kointestapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nennos.kointestapp.databinding.FrUserBinding
import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.utils.Const.BUNDLE_USER_ID_KEY
import kotlinx.android.synthetic.main.fr_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private val userModel: UserViewModel by viewModel()
    private lateinit var userLiveData: LiveData<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().apply {
            userLiveData = userModel.loadUser(getLong(BUNDLE_USER_ID_KEY))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FrUserBinding.inflate(inflater, container, false)
        binding.viewModel = userModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userLiveData.observe(viewLifecycleOwner, Observer {
            userModel.setupData(it)
            loadAvatar(it.avatarUrl)

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        userLiveData.removeObservers(this)
    }

    fun loadAvatar(url: String) {
        Glide.with(requireContext())
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(userAvatarImg)
    }
}