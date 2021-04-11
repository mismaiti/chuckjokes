package com.mismaiti.chuckjokes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mismaiti.chuckjokes.data.JokesEntity

@Database(entities = [JokesEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun jokesDao(): JokesDao
}