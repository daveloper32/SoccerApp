package com.daveloper.soccerapp.auxiliar.ext_fun

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat



fun isNull (x: Any) : Boolean {
    return x == null
}

fun Activity.color(@ColorRes color: Int) : Int {
    return ContextCompat.getColor(this, color)
}

fun Context.color(@ColorRes color: Int) : Int {
    return ContextCompat.getColor(this, color)
}

@RequiresApi(Build.VERSION_CODES.M)
fun Context.getColorResource(@ColorRes colorRes: Int,
                             resTheme: Resources.Theme = this.theme
) : Int {
    return this.resources.getColor(colorRes, resTheme)
}

fun Context.getStringResource(stringRes: Int) : String {
    return this.resources.getText(stringRes).toString()
}

fun hideKeyboard(context: Context, activity: Activity) {
    // https://stackoverflow.com/questions/1109022/how-do-you-close-hide-the-android-soft-keyboard-programmatically
    val imm: InputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = activity.currentFocus
    if (view == null) {
        view = View(activity);
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}