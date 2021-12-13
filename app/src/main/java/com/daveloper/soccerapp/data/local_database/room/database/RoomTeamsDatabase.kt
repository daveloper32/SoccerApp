package com.daveloper.soccerapp.data.local_database.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.daveloper.soccerapp.data.local_database.room.dao.TeamDao
import com.daveloper.soccerapp.data.model.entity.Team

@Database (
    entities = [Team::class],
    version = 2,
    exportSchema = false
)

abstract class RoomTeamsDatabase
    : RoomDatabase() {
    abstract fun teamDao() : TeamDao

    companion object {
        @Volatile
        private var INSTANCE: RoomTeamsDatabase? = null

        fun getDatabase (
            context: Context
        ) : RoomTeamsDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(
                        context.applicationContext,
                        RoomTeamsDatabase::class.java,
                        "data_base"
                    ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}