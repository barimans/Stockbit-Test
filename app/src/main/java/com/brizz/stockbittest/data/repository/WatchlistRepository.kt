package com.brizz.stockbittest.data.repository

import com.brizz.stockbittest.data.models.DataItem
import com.brizz.stockbittest.data.models.WatchlistResponse
import io.reactivex.Observable

interface WatchlistRepository {

    fun getDataWatchList(): Observable<MutableList<DataItem>>
}