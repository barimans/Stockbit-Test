package com.brizz.stockbittest.feature.main.watchlist

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brizz.stockbittest.data.models.DataItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WatchlistViewModel@Inject constructor(
    private val interactor: WatchlistIntercator
): ViewModel(), WatchlistContract.ViewModel {

    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()
    val listDataWatchlist = MutableLiveData<MutableList<DataItem>>()

    @SuppressLint("CheckResult")
    override fun getWatchList() {
        isLoading.postValue(true)
        interactor.getWatchList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                isLoading.postValue(false)
                listDataWatchlist.postValue(result)
            }, { error ->
                isLoading.postValue(false)
                isError.postValue(error)
            })
    }

}