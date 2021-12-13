package com.daveloper.soccerapp.ui.view.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daveloper.soccerapp.auxiliar.ext_fun.goToXActivity
import com.daveloper.soccerapp.ui.view.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToXActivity(MainActivity::class.java)
    }
}