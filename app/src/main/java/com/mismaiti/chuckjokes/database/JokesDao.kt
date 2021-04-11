package com.mismaiti.chuckjokes.database

import androidx.annotation.WorkerThread
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mismaiti.chuckjokes.data.JokesEntity

@Dao
interface JokesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @WorkerThread
    fun insertJokes(listJokes: List<JokesEntity>)

    @Query("SELECT * FROM `JokesEntity`")
    fun getJokes(): List<JokesEntity>
}