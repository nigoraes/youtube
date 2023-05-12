package com.example.youtube.data.remote

import com.example.youtube.data.remote.model.Playlist
import com.example.youtube.data.remote.model.PlaylistItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylist(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResult: Int,
    ): Call<Playlist>

    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("key") apiKey: String,
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResult: Int,

        ): Call<PlaylistItems>

}


