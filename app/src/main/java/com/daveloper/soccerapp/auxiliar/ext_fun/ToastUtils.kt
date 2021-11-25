package com.daveloper.soccerapp.auxiliar.ext_fun

import android.app.Activity
import android.widget.Toast

fun Activity.toast (mensaje: String, longitud : Int = Toast.LENGTH_SHORT) {
    Toast.makeText(
        this,
        mensaje,
        longitud
    ).show()
}