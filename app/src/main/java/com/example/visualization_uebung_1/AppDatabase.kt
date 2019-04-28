package com.example.visualization_uebung_1

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ResultData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ResultDao
}
