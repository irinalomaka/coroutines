package com.nennos.kointestapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nennos.kointestapp.R
import com.nennos.kointestapp.db.models.User
import com.nennos.kointestapp.utils.OnItemListener
import kotlinx.android.synthetic.main.li_user.view.*
import java.util.*

class UsersAdapter(private val listener: OnItemListener) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var content = mutableListOf<User>()

    fun submitList(users: List<User>) {
        content.clear()
        content.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.li_user,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = content.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(content[position])
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.userName.text  = user.login.toUpperCase(Locale.US)
            itemView.userType.text = user.type.toUpperCase(Locale.US)

            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(itemView.userAvatarImg)

            itemView.liContainer.setOnClickListener { listener.onItemClicked(user) }

        }
    }
}