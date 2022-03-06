package com.sonicjar.media.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sonicjar.media.data.Track
import com.sonicjar.media.databinding.LayoutTrackRowBinding

class TrackAdapter(var list: ArrayList<Track>) : RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    class DiffUtilCallback : DiffUtil.ItemCallback<Track>() {

        override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
            return false
        }
        override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
            return oldItem == newItem
        }
    }

    var listener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].let { holder.onBind(it, position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutTrackRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val mLayoutBinding: LayoutTrackRowBinding) : RecyclerView.ViewHolder(mLayoutBinding.root) {

        fun onBind(item: Track, position: Int) {

            if(position == 0){
                mLayoutBinding.paddingView.visibility = View.VISIBLE
            }
            else{
                mLayoutBinding.paddingView.visibility = View.GONE
            }

            mLayoutBinding.viewModel = item
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(data: Track)
    }
}