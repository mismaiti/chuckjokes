package com.mismaiti.chuckjokes.api

import com.mismaiti.chuckjokes.data.JokesEntity
import com.mismaiti.chuckjokes.data.JokesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesApi {

    @GET("random")
    fun getRandomJokes() : Observable<JokesEntity>

    @GET("random")
    fun getRandomJokesByCategory(
        @Query("category") category: String
    ) : Observable<JokesEntity>

    @GET("categories")
    fun getJokeCategories() : Observable<List<String>>

    @GET("search")
    fun getJokesByKeyword(
        @Query("query") keyword: String
    ) : Observable<JokesResponse>
}
