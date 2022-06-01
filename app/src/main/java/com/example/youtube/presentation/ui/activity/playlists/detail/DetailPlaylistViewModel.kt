package com.example.youtube.presentation.ui.activity.playlists.detail

import androidx.lifecycle.LiveData
import com.example.youtube.core.network.status.Resource
import com.example.youtube.data.remote.dto.Playlist
import com.example.youtube.data.repositories.DetailPlaylistRepository
import com.example.youtube.presentation.ui.base.BaseViewModel

class DetailPlaylistViewModel(private val repository: DetailPlaylistRepository) : BaseViewModel() {
    private lateinit var data: LiveData<Resource<Playlist>>

    fun getPlaylists(id: String): LiveData<Resource<Playlist>> {
        return playlist(id)
    }

    private fun playlist(id: String): LiveData<Resource<Playlist>> {
        data = repository.getPlayList(id)
        return data
    }
}