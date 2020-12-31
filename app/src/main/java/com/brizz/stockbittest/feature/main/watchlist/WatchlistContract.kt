package com.brizz.stockbittest.feature.main.watchlist

import android.content.Context
import com.brizz.stockbittest.data.models.DataItem
import io.reactivex.Observable

interface WatchlistContract {
    interface View{
        fun observeLoading(isLoading: Boolean?)

        fun observeError(error: Throwable?)

        fun observeFavoriteUsers(watchList: MutableList<DataItem>)
    }

    interface Interactor{
        fun getWatchList() : Observable<MutableList<DataItem>>
    }

    interface ViewModel{
        fun getWatchList()
    }
}