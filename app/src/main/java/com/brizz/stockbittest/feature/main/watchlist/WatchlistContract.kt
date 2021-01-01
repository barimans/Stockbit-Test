package com.brizz.stockbittest.feature.main.watchlist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.brizz.stockbittest.data.models.DataItem
import io.reactivex.Observable

interface WatchlistContract {
    interface View{
        fun observeShowLoading()

        fun observeHideLoading()

        fun observeError(error: String)

        fun observeFavoriteUsers(watchList: PagedList<DataItem>)
    }
}