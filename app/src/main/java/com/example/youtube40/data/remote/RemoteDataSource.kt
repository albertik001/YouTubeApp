package com.example.youtube40.data.remote

import com.example.youtube40.BuildConfig.API_KEY
import com.example.youtube40.common.constants.Constant
import com.example.youtube40.core.network.BaseDataSource
import com.example.youtube40.data.remote.apiservices.ApiService

class RemoteDataSource(private val apiService: ApiService) : BaseDataSource() {

    suspend fun getPlaylist() = getResult {
        apiService.getPlaylists(Constant.part, API_KEY, Constant.channelId, 50)
    }

    suspend fun getPlaylistItem(id: String) = getResult {
        apiService.getPlaylistItem(Constant.part, API_KEY, id, 50)
    }

}