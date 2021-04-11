package com.mismaiti.chuckjokes.di.module

import android.app.Application
import androidx.room.Room
import com.mismaiti.chuckjokes.database.AppDatabase
import com.mismaiti.chuckjokes.database.JokesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application, AppDatabase::class.java, "ChuckJokes.db")
            .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun providesJokesDao(appDatabase: AppDatabase): JokesDao {
        return appDatabase.jokesDao()
    }
}