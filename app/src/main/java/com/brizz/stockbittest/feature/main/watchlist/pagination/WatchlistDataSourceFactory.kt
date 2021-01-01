package com.brizz.stockbittest.feature.main.watchlist.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.brizz.stockbittest.data.models.DataItem
import com.brizz.stockbittest.data.repository.impl.WatchlistRepositoryImpl
import javax.inject.Inject

class WatchlistDataSourceFactory@Inject constructor(
    private val repositoryImpl: WatchlistRepositoryImpl
): DataSource.Factory<Int, DataItem>() {

    val watchlistLiveData = MutableLiveData<WatchlistDataSource>()

    override fun create(): DataSource<Int, DataItem> {
        val source = WatchlistDataSource(repositoryImpl)
        watchlistLiveData.postValue(source)
        return source
    }
}