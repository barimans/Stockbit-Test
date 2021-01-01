package com.brizz.stockbittest.feature.main.watchlist

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import com.brizz.stockbittest.feature.main.watchlist.pagination.WatchlistDataSource
import com.brizz.stockbittest.feature.main.watchlist.pagination.WatchlistDataSourceFactory
import javax.inject.Inject

class WatchlistViewModel@Inject constructor(
    dataSourceFactory: WatchlistDataSourceFactory
): ViewModel(){

    val listDataWatchlist = dataSourceFactory.toLiveData(pageSize = 10)
    val viewStateLoad = Transformations.switchMap(dataSourceFactory.watchlistLiveData, WatchlistDataSource::getViewStates)

}