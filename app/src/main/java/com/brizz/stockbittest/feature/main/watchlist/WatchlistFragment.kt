package com.brizz.stockbittest.feature.main.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.brizz.stockbittest.R
import com.brizz.stockbittest.base.BaseFragment
import com.brizz.stockbittest.data.models.DataItem
import com.brizz.stockbittest.feature.main.watchlist.adapter.WatchlistAdapter
import com.brizz.stockbittest.utils.injectViewModel
import kotlinx.android.synthetic.main.fragment_watchlist.*

class WatchlistFragment : BaseFragment<WatchlistViewModel>(), WatchlistContract.View {

    override fun injectViewModel() {
        mViewModel = injectViewModel(viewModelfactory)
    }

    override fun getViewModelClass(): Class<WatchlistViewModel> = WatchlistViewModel::class.java

    override fun getLayoutResorceId(): Int = R.layout.fragment_watchlist

    private var dataWatchlistAdapter: WatchlistAdapter = WatchlistAdapter(clickListener = { data : DataItem -> watchlistClicked(data)})

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
                observeLoading(isLoading)
            })
            isError.observe(viewLifecycleOwner, Observer { error ->
                observeError(error)
            })
            listDataWatchlist.observe(viewLifecycleOwner, Observer { listData ->
                observeFavoriteUsers(listData)
            })
        }

        viewModel.getWatchList()

        initRecyclerView()
    }

    private fun initRecyclerView(){
        rvWatchlist.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = dataWatchlistAdapter
        }
    }

    override fun observeLoading(isLoading: Boolean?) {
        isLoading?.let {
            if (it){
                rvWatchlist.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }else{
                rvWatchlist.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun observeError(error: Throwable?) {
        error?.let { Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show() }
    }

    override fun observeFavoriteUsers(watchList: MutableList<DataItem>) {
        dataWatchlistAdapter.data = watchList
        dataWatchlistAdapter.notifyDataSetChanged()
    }

    private fun watchlistClicked(data: DataItem) {}
}