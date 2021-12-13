package com.daveloper.soccerapp.core

import android.content.Context
import androidx.room.Room
import com.daveloper.soccerapp.data.local_database.room.dao.TeamDao
import com.daveloper.soccerapp.data.local_database.room.database.RoomTeamsDatabase
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
object RoomHelper {

    @Singleton
    @Provides
    fun teamsDatabase (
        @ApplicationContext context: Context
    ): RoomTeamsDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                RoomTeamsDatabase::class.java,
                "data_base"
            ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun teamDAO(
        roomTeamsDatabase: RoomTeamsDatabase
    ): TeamDao {
        return roomTeamsDatabase.teamDao()
    }
}