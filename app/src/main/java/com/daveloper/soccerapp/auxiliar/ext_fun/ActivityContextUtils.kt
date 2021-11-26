package com.daveloper.soccerapp.auxiliar.ext_fun

import android.app.Activity
import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Activity.color(@ColorRes color: Int) : Int {
    return ContextCompat.getColor(this, color)
}

fun Context.color(@ColorRes color: Int) : Int {
    return ContextCompat.getColor(this, color)
}

fun Context.getStringResource(stringRes: Int) : String {
    return this.resources.getText(stringRes).toString()
}
