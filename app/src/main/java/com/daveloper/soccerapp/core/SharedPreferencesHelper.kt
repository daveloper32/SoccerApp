package com.daveloper.soccerapp.core

import android.content.Context
import com.daveloper.soccerapp.data.local_database.shared_prefs.UserLocalData
import com.daveloper.soccerapp.ui.view.SoccerApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SharedPreferencesHelper {

    @Singleton
    @Provides
    fun userLocalData (
        @ApplicationContext context: Context
    ) : UserLocalData {
        return UserLocalData(context)
    }
}