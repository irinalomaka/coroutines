package com.nennos.kointestapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nennos.kointestapp.R
import com.nennos.kointestapp.databinding.FrMainBinding
import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.ui.adapter.UsersAdapter
import com.nennos.kointestapp.utils.OnItemListener
import kotlinx.android.synthetic.main.fr_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), OnItemListener {

    private val mainModel: MainFragmentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainModel.fetchUsers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FrMainBinding.inflate(inflater, container, false)
        binding.viewModel = mainModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            adapter = UsersAdapter(this@MainFragment)
            layoutManager = LinearLayoutManager(context)
        }

        mainModel.usersLiveData.observe(viewLifecycleOwner, Observer {
            mainModel.userList.value = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainModel.usersLiveData.removeObservers(this)
    }

    override fun onItemClicked(user: User) {
        view?.findNavController()?.navigate(R.id.action_mainFragment_to_userFragment)
    }

}