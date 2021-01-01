package com.brizz.stockbittest.data.remote

import com.brizz.stockbittest.data.models.WatchlistResponse
import io.reactivex.Observable

interface RemoteDataSource {

    fun getDataWatchList(page: Int): Observable<WatchlistResponse>

}