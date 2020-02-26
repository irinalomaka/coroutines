package com.nennos.kointestapp.ui.base

import android.content.DialogInterface
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.nennos.kointestapp.R

open class BaseFragment : Fragment() {

    protected fun showAlertDialog(
        title: String,
        message: String?,
        positiveBtnText: String,
        positiveButtonClickListener: DialogInterface.OnClickListener
    ) {
        activity?.let {
            val dialog = AlertDialog.Builder(it, R.style.AlertDialog)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(positiveBtnText, positiveButtonClickListener)
                .show()

            val positiveBtn = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveBtn.setTextColor(ContextCompat.getColor(it, R.color.colorAccent))

            val textView = dialog.findViewById<View>(android.R.id.message) as TextView?
            textView!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.0f)
        }
    }
}