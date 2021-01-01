package com.brizz.stockbittest.feature.main.watchlist.pagination

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.brizz.stockbittest.data.models.DataItem
import com.brizz.stockbittest.data.models.ViewState
import com.brizz.stockbittest.data.repository.impl.WatchlistRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WatchlistDataSource@Inject constructor(
    private val repositoryImpl: WatchlistRepositoryImpl
): PageKeyedDataSource<Int, DataItem>() {

    private val viewState = MutableLiveData<ViewState>()

    fun getViewStates() = viewState

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataItem>
    ) {
        viewState.postValue(ViewState.LOADING)
        repositoryImpl.getDataWatchList(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                viewState.postValue(ViewState.LOADED)
                callback.onResult(result, null, 2)
            }, { error ->
                callback.onResult(List(0) { null }, null, null)
                viewState.postValue(ViewState(ViewState.Status.FAILED, error.message.toString()))
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataItem>) {
        viewState.postValue(ViewState.LOADING)
        val page = params.key
        repositoryImpl.getDataWatchList(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                viewState.postValue(ViewState.LOADED)
                callback.onResult(result, page + 1)
            }, { error ->
                callback.onResult(List(0) { null }, null)
                viewState.postValue(ViewState(ViewState.Status.FAILED, error.message.toString()))
            })
    }

    @SuppressLint("CheckResult")
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataItem>) {
        viewState.postValue(ViewState.LOADING)
        val page = params.key
        repositoryImpl.getDataWatchList(page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ result ->
                viewState.postValue(ViewState.LOADED)
                callback.onResult(result, page - 1)
            }, { error ->
                callback.onResult(List(0) { null }, null)
                viewState.postValue(ViewState(ViewState.Status.FAILED, error.message.toString()))
            })
    }
}