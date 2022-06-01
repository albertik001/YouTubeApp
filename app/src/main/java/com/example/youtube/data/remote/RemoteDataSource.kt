package com.example.youtube.data.remote

import com.example.youtube.common.constants.Constant
import com.example.youtube.core.network.BaseDataSource
import com.example.youtube.data.remote.apiservices.ApiService
import youtube.BuildConfig.API_KEY

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getPlaylist() = getResult {
        apiService.getPlaylists(Constant.part, API_KEY, Constant.channelId, 50)
    }

    suspend fun getPlaylistItem(id: String) = getResult {
        apiService.getPlaylistItem(Constant.part, API_KEY, id, 50)
    }

}