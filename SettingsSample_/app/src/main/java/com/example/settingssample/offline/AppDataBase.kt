package com.example.settingssample.offline

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun todoData():DataDAO

    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                        AppDataBase::class.java, "app_database.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as AppDataBase
        }
    }
}