package com.brizz.stockbittest.feature.main.watchlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.brizz.stockbittest.R
import com.brizz.stockbittest.data.models.DataItem
import kotlinx.android.synthetic.main.item_watchlist.view.*

class WatchlistAdapter(val clickListener: (DataItem?) -> Unit)
    : PagedListAdapter<DataItem, WatchlistAdapter.WatchlistViewHolder>(WatchlistDiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): WatchlistViewHolder {
        return WatchlistViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_watchlist, viewGroup, false))
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        holder.bindData(getItem(position), clickListener)
    }

    class WatchlistViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(data: DataItem?, clickListener: (DataItem?) -> Unit) {
            itemView.txtCodeName.text = data?.coinInfo?.name
            itemView.txtFullName.text = data?.coinInfo?.fullName
            itemView.txtValue.text = data?.raw?.rawUSD?.price.toString()
            itemView.txtPercent.text = data?.display?.usd?.changePctHour

            itemView.setOnClickListener {
                clickListener(data)
            }
        }
    }

}

private class WatchlistDiffCallback : DiffUtil.ItemCallback<DataItem>() {

    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.coinInfo?.id == newItem.coinInfo?.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}