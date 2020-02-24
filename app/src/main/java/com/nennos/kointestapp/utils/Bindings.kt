package com.nennos.kointestapp.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.ui.adapter.UsersAdapter

@BindingAdapter("app:userList")
fun submitUserList(recyclerView: RecyclerView, list: List<User>) {
    (recyclerView.adapter as UsersAdapter).submitList(list)
}