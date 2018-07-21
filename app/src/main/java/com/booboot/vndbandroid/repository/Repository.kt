package com.booboot.vndbandroid.repository

import io.reactivex.Completable
import io.reactivex.Single

abstract class Repository<T> {
    protected var items = mutableMapOf<Int, T>()

    open fun getItems(): Single<Map<Int, T>> = Single.error(Throwable("not implemented"))
    open fun getItems(ids: List<Int>): Single<Map<Int, T>> = Single.error(Throwable("not implemented"))
    open fun setItems(items: List<T>): Completable = Completable.error(Throwable("not implemented"))
}