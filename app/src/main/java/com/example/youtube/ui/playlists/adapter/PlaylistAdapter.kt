package com.example.youtube.ui.playlists.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youtube.databinding.ItemPlaylistBinding
import com.example.youtube.loadImage
import com.example.youtube.model.Playlist

class PlaylistsAdapter(private val onClick: (Playlist.Item) -> Unit) :
    RecyclerView.Adapter<PlaylistsAdapter.PlaylistsViewHolder>() {

    private var list = ArrayList<Playlist.Item>()


    fun addList(list: List<Playlist.Item>) {
        this.list = list as ArrayList<Playlist.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(
            ItemPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class PlaylistsViewHolder(private val binding: ItemPlaylistBinding) :
        ViewHolder(binding.root) {

        fun bind(item: Playlist.Item) {
            with(binding) {
                tvPlaylistName.text = item.snippet?.title
                tvCountVideo.text = item.contentDetails?.itemCount.toString() + " video series"
                ivPlaylist.loadImage(item.snippet?.thumbnails?.default?.url!!)
                cvPlaylist.setOnClickListener {
                    onClick.invoke(item)
                }
            }
        }
    }
}