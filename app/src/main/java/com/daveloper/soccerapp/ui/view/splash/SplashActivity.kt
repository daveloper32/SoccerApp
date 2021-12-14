package com.daveloper.soccerapp.ui.view.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daveloper.soccerapp.auxiliar.ext_fun.goToXActivity
import com.daveloper.soccerapp.ui.view.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        goToXActivity(MainActivity::class.java)
    }
}