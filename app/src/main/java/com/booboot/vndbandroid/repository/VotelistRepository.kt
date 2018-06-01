package com.booboot.vndbandroid.repository

import com.booboot.vndbandroid.dao.DB
import com.booboot.vndbandroid.model.vndb.Votelist
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VotelistRepository @Inject constructor(var db: DB) : ListRepository<Votelist>() {
    override fun getItemsFromDB(): List<Votelist> = db.votelistDao().findAll()

    override fun addItemsToDB(items: List<Votelist>) = db.votelistDao().insertAll(items)
}