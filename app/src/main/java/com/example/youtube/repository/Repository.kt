package com.example.youtube.repository

import retrofit2.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube.BuildConfig
import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.ApiService
import com.example.youtube.core.network.RetrofitClient
import com.example.youtube.core.network.result.Resource
import com.example.youtube.data.remote.model.PlaylistItems
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylist(): LiveData<Resource<Playlist>> {
        val data = MutableLiveData<Resource<Playlist>>()
        data.value = Resource.loading()


        apiService.getPlaylist(BuildConfig.API_KEY,
            "contentDetails,snippet",
            "UCRdiJLVX3cU6d2tQsvXM87A",
            30)
            .enqueue(object : Callback<Playlist> {
                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    data.value = Resource.error(t.message.toString(), null, null)
                    print(t.stackTrace)
                }
            })
        return data
    }

    fun getPlaylistItems(id: String): LiveData<Resource<PlaylistItems>> {
        val data = MutableLiveData<Resource<PlaylistItems>>()
        data.value = Resource.loading()


        apiService.getPlaylistItems(BuildConfig.API_KEY,
            "contentDetails,snippet",
            id,
            30)
            .enqueue(object : Callback<PlaylistItems> {
                override fun onResponse(
                    call: Call<PlaylistItems>,
                    response: Response<PlaylistItems>,
                ) {
                    if (response.isSuccessful) {
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<PlaylistItems>, t: Throwable) {
                    data.value = Resource.error(t.message.toString(), null, null)
                    print(t.stackTrace)
                }
            })
        return data
    }
}


