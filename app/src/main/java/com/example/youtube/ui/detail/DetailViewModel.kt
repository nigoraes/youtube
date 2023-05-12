package com.example.youtube.ui.detail

import androidx.lifecycle.LiveData
import com.example.youtube.App
import com.example.youtube.core.network.result.Resource
import com.example.youtube.core.ui.BaseViewModel
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.model.PlaylistItems

class DetailViewModel: BaseViewModel() {

    fun getPlaylistItem(id:String): LiveData<Resource<PlaylistItems>> {
        return App.repository.getPlaylistItems(id)
    }

}