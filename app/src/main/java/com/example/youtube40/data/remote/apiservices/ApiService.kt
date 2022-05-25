package com.example.youtube40.data.remote.apiservices

import com.example.youtube40.data.remote.dto.Playlist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int,
    ): Response<Playlist>

    @GET("playlistItems")
    suspend fun getPlaylistItem(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int,
    ): Response<Playlist>


}