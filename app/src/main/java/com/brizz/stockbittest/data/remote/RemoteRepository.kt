package com.brizz.stockbittest.data.remote

import com.brizz.stockbittest.data.models.WatchlistResponse
import io.reactivex.Observable

class RemoteRepository : RemoteDataSource {

    private var apiService: RemoteService? = null

    init {
        apiService = RemoteService.create()
    }

    companion object {
        @Synchronized
        fun getInstance(): RemoteRepository {
            return RemoteRepository()
        }

    }

    override fun getDataWatchList(page: Int): Observable<WatchlistResponse> {
        return apiService!!.getDataWatchList(page = page)
    }
}