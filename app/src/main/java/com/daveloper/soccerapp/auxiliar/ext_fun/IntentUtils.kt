package com.daveloper.soccerapp.auxiliar.ext_fun

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun Activity.goToXActivity(activity: Class<out AppCompatActivity?>, finishActivity: Boolean = true){
    // Creamos un objeto de la clase Intent para que al presionar el boton vayamos al Activity que queramos ir
    val i: Intent = Intent(this, activity)
    // Iniciamos el Activity al que queremos ir
    startActivity(i)
    if (finishActivity) {
        this.finish()
    }
}