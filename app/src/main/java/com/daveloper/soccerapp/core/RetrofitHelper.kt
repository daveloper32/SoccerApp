package com.daveloper.soccerapp.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitHelper {
    private val apiTheSportsDB: String =
        "https://www.thesportsdb.com/api/v1/json/2/"

    @Singleton
    @Provides
    fun getRetrofit() : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(apiTheSportsDB)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}