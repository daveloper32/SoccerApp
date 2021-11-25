package com.daveloper.soccerapp.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(apiToUse: String) : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(apiToUse)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}