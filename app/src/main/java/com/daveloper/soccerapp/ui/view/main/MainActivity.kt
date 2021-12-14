package com.daveloper.soccerapp.ui.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daveloper.soccerapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}