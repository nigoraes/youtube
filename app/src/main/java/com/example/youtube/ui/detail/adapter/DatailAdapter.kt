package com.example.youtube.ui.detail.adapter

import android.annotation.SuppressLint
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youtube.databinding.ItemPlaylistBinding
import com.example.youtube.loadImage
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.model.PlaylistItems
import com.example.youtube.databinding.ItemVideosBinding

class DatailAdapter :
    RecyclerView.Adapter<DatailAdapter.DatailViewHolder>() {

    private var list = ArrayList<PlaylistItems.Item>()

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<PlaylistItems.Item>) {
        this.list = list as ArrayList<PlaylistItems.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatailViewHolder {
        return DatailViewHolder(
                ItemVideosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DatailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class DatailViewHolder(private val binding: ItemVideosBinding) :
        ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: PlaylistItems.Item) {
            with(binding) {
                tvPlaylistName.text = item.snippet?.title
                ivPlaylist.loadImage(item.snippet?.thumbnails?.default?.url!!)
            }
        }
    }
}
