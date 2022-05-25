package com.example.youtube40.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube40.core.network.status.Resource
import com.example.youtube40.data.remote.RemoteDataSource
import com.example.youtube40.data.remote.dto.Playlist
import kotlinx.coroutines.Dispatchers

class PlaylistRepository(private val dataSource: RemoteDataSource) {

    fun getPlayList(): LiveData<Resource<Playlist>> = liveData(Dispatchers.IO) {
        emit(Resource.loading()) // emit observes thread and returns data
        val response = dataSource.getPlaylist()
        emit(response)
    }
}