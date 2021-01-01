package com.brizz.stockbittest.feature.main.watchlist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.brizz.stockbittest.R
import com.brizz.stockbittest.base.BaseFragment
import com.brizz.stockbittest.data.models.DataItem
import com.brizz.stockbittest.data.models.ViewState
import com.brizz.stockbittest.data.repository.impl.WatchlistRepositoryImpl
import com.brizz.stockbittest.feature.main.watchlist.adapter.WatchlistAdapter
import com.brizz.stockbittest.feature.main.watchlist.pagination.WatchlistDataSourceFactory
import com.brizz.stockbittest.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_watchlist.*

class WatchlistFragment : BaseFragment<WatchlistViewModel>(), WatchlistContract.View {

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelfactory)
    }

    override fun getViewModelClass(): Class<WatchlistViewModel> = WatchlistViewModel::class.java

    override fun getLayoutResorceId(): Int = R.layout.fragment_watchlist

    private var dataWatchlistAdapter: WatchlistAdapter = WatchlistAdapter(clickListener = { data : DataItem? -> watchlistClicked(data)})

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            listDataWatchlist.observe(viewLifecycleOwner, Observer { listData ->
                swipeRefresh.isRefreshing = false
                observeFavoriteUsers(listData)
            })
            viewStateLoad.observe(viewLifecycleOwner, Observer {
                when(it){
                    ViewState.LOADING -> observeShowLoading()
                    ViewState.LOADED -> observeHideLoading()
                    else -> observeError(it.msg)
                }
            })
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        initRecyclerView()
    }

    private fun initRecyclerView(){
        rvWatchlist.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = dataWatchlistAdapter
        }
    }

    override fun observeFavoriteUsers(watchList: PagedList<DataItem>) {
        dataWatchlistAdapter.submitList(watchList)
    }

    override fun observeShowLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun observeHideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun observeError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }

    private fun watchlistClicked(data: DataItem?) {}
}