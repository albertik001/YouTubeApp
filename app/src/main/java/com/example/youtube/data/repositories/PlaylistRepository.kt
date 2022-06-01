package com.example.youtube.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube.core.network.status.Resource
import com.example.youtube.data.remote.RemoteDataSource
import com.example.youtube.data.remote.dto.Playlist
import kotlinx.coroutines.Dispatchers

class PlaylistRepository(private val dataSource: RemoteDataSource) {

    fun getPlayList(): LiveData<Resource<Playlist>> = liveData(Dispatchers.IO) {
        emit(Resource.loading()) // emit observes thread and returns data
        val response = dataSource.getPlaylist()
        emit(response)
    }
}