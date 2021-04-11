package com.mismaiti.chuckjokes.repo

import com.mismaiti.chuckjokes.api.JokesService
import com.mismaiti.chuckjokes.data.JokesEntity
import com.mismaiti.chuckjokes.data.DataBoundResource
import com.mismaiti.chuckjokes.database.JokesDao
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Singleton

@Singleton
class JokesRepository(
    private val jokesDao: JokesDao,
    private val jokesService: JokesService) {

    fun loadJokes(): Observable<List<JokesEntity>> {
        return object : DataBoundResource<List<JokesEntity>, List<JokesEntity>>() {

            override fun saveCallResult(item: List<JokesEntity>) {
                jokesDao.insertJokes(item)
            }

            override fun shouldFetch(): Boolean {
                return true
            }

            override fun loadFromDb(): Flowable<List<JokesEntity>> {
                val jokesEntityList = jokesDao.getJokes()
                return if (jokesEntityList.isEmpty()) {
                    Flowable.empty()
                } else {
                    Flowable.just(jokesEntityList)}
            }

            override fun createCall(): Observable<List<JokesEntity>> {
                return jokesService.getJokesCategories().concatMap { categoryList ->
                    val listJokesEntity: ArrayList<JokesEntity> = ArrayList()
                    var generatedId: Int = 0
                    categoryList.forEach {
                        jokesService.getRandomJokesByCategory(it)
                            .subscribe { jokes ->
                                jokes.id = generatedId++
                                jokes.category = it
                                listJokesEntity.add(jokes)
                            }
                    }
                    return@concatMap Observable.just(listJokesEntity)
                }

            }
        }.getAsObservable()
    }
}