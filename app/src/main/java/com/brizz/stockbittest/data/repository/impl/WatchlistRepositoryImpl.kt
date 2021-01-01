package com.brizz.stockbittest.data.repository.impl

import com.brizz.stockbittest.data.models.DataItem
import com.brizz.stockbittest.data.models.WatchlistResponse
import com.brizz.stockbittest.data.remote.RemoteRepository
import com.brizz.stockbittest.data.repository.WatchlistRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WatchlistRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteRepository
) : WatchlistRepository{

    override fun getDataWatchList(page: Int): Observable<MutableList<DataItem>> {
        return remoteSource.getDataWatchList(page = page).map { it.data }
    }
}