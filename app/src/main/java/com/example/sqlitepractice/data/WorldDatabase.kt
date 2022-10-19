package com.example.sqlitepractice.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [City::class, Country::class], version = 7)
abstract class WorldDatabase:RoomDatabase(){
    abstract val worldDao: WorldDao

    companion object {
        @Volatile
        private var INSTANCE: WorldDatabase? = null

        fun getInstance(context: Context): WorldDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(context.applicationContext, WorldDatabase::class.java, "world_db")
                    .build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}