package com.mismaiti.chuckjokes.data

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class DataBoundResource<ResultType, RequestType> @MainThread
protected constructor() {

    private val asObservable: Observable<ResultType>
    init {
        val source: Observable<ResultType>
        if (shouldFetch()) {
            source = createCall()
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    saveCallResult(processResponse(it)!!) }
                .flatMap {
                    loadFromDb().toObservable()
                        .map { it } }
                .doOnError {
                    onFetchFailed()
                }
                .onErrorResumeNext {
                        t : Throwable ->
                    loadFromDb().toObservable().map {
                        it
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
        } else {
            source = loadFromDb()
                .toObservable()
                .map { it }
        }

        asObservable = Observable.concat(
            loadFromDb()
                .toObservable()
                .map { it }
                .take(1),
            source
        )
    }

    fun getAsObservable(): Observable<ResultType> {
        return asObservable
    }

    private fun onFetchFailed() {}

    @WorkerThread
    protected fun processResponse(response: RequestType): RequestType? {
        return response
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flowable<ResultType>

    @MainThread
    protected abstract fun createCall(): Observable<RequestType>
}