package com.nennos.kointestapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nennos.kointestapp.utils.Const.BUNDLE_USER_ID_KEY

class UserFragment : Fragment() {




    companion object {

        fun newInstance(
            userId: Long
        ) = UserFragment().apply {
            arguments = Bundle().apply {
                putLong(BUNDLE_USER_ID_KEY, userId)
            }
        }
    }
}