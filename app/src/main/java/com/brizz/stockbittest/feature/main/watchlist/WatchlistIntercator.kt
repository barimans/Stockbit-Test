package com.brizz.stockbittest.feature.main.watchlist

import com.brizz.stockbittest.data.models.DataItem
import com.brizz.stockbittest.data.repository.impl.WatchlistRepositoryImpl
import io.reactivex.Observable
import javax.inject.Inject

class WatchlistIntercator @Inject constructor(
    private val repositoryImpl: WatchlistRepositoryImpl
): WatchlistContract.Interactor {
    override fun getWatchList(): Observable<MutableList<DataItem>> {
        return repositoryImpl.getDataWatchList()
    }
}