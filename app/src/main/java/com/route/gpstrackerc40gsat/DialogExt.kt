package com.route.gpstrackerc40gsat

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

fun Activity.showDialog(
    title: String,
    positiveText: String,
    onPositiveClickListener: DialogInterface.OnClickListener,
    negativeText: String? = null,
    onNegativeClickListener: DialogInterface.OnClickListener? = null
) {
    val dialog = AlertDialog.Builder(this)
    dialog.setTitle(title)
    dialog.setPositiveButton(positiveText, onPositiveClickListener)
    if (onNegativeClickListener != null) {
        dialog.setNegativeButton(negativeText ?: "", onNegativeClickListener)
    }
    dialog.show()

}