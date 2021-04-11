package com.mismaiti.chuckjokes.api

class JokesService(private val jokesApi: JokesApi) {

    fun getRandomJokes() = jokesApi.getRandomJokes()

    fun getRandomJokesByCategory(category: String) = jokesApi.getRandomJokesByCategory(category)

    fun getJokesCategories() = jokesApi.getJokeCategories()

    fun getJokesByKeyword(keyword: String) = jokesApi.getJokesByKeyword(keyword)
}