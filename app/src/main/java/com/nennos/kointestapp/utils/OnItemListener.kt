package com.nennos.kointestapp.utils

import com.nennos.kointestapp.db.models.User

interface OnItemListener {
    fun onItemClicked(user: User)
}
