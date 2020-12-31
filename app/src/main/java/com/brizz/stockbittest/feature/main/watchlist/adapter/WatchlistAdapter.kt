package com.brizz.stockbittest.feature.main.watchlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brizz.stockbittest.R
import com.brizz.stockbittest.data.models.DataItem
import kotlinx.android.synthetic.main.item_watchlist.view.*

class WatchlistAdapter(var data: List<DataItem> = listOf(), val clickListener: (DataItem) -> Unit) : RecyclerView.Adapter<WatchlistViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): WatchlistViewHolder {
        return WatchlistViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_watchlist, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        holder.bindData(data[position], clickListener)
    }

}

class WatchlistViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(data: DataItem, clickListener: (DataItem) -> Unit) {
        itemView.txtCodeName.text = data.coinInfo?.name
        itemView.txtFullName.text = data.coinInfo?.fullName
        itemView.txtValue.text = data.raw?.rawUSD?.price.toString()
        itemView.txtPercent.text = data.display?.usd?.changePctHour

        itemView.setOnClickListener {
            clickListener(data)
        }
    }
}