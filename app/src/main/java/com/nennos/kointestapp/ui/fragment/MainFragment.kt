package com.nennos.kointestapp.ui.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nennos.kointestapp.R
import com.nennos.kointestapp.databinding.FrMainBinding
import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.ui.adapter.UsersAdapter
import com.nennos.kointestapp.ui.base.BaseFragment
import com.nennos.kointestapp.utils.OnItemListener
import kotlinx.android.synthetic.main.fr_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(), OnItemListener, DialogInterface.OnClickListener {

    private val mainModel: MainFragmentViewModel by viewModel()

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

        mainModel.errorLiveDatata.observe(viewLifecycleOwner, Observer {
            showAlertDialog(
                getString(R.string.title_error),
                it,
                getString(R.string.button_ok),
                this@MainFragment
            )
        })
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {

    }

    override fun onItemClicked(user: User) {
        view?.findNavController()?.navigate(
            R.id.action_mainFragment_to_userFragment,
            MainFragmentDirections.actionMainFragmentToUserFragment().setUserId(user.id.toString()).arguments
        )
    }
}