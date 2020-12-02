package com.example.arcitecturemvvm.util

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).apply {
        show()
    }
}

fun loadAnimation(context: Context, resId: Int): Animation {
    return AnimationUtils.loadAnimation(context, resId)
}

/**
 * SnackBar
 * */
fun View.snackbar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.setAction("okay") {
            snackbar.dismiss()
        }
    }.show()
}