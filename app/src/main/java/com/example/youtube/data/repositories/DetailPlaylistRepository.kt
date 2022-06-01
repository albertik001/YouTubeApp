package com.example.youtube.data.repositories

import androidx.lifecycle.liveData
import com.example.youtube.core.network.status.Resource
import com.example.youtube.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers

class DetailPlaylistRepository(private val dataSource: RemoteDataSource) {

    fun getPlayList(id: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading()) // emit observes thread and returns data
        val response = dataSource.getPlaylistItem(id)
        emit(response)
    }
}