package com.example.youtube.presentation.ui.activity.playlists

import androidx.lifecycle.LiveData
import com.example.youtube.core.network.status.Resource
import com.example.youtube.data.remote.dto.Playlist
import com.example.youtube.data.repositories.PlaylistRepository
import com.example.youtube.presentation.ui.base.BaseViewModel

class PlaylistViewModel(private val repository: PlaylistRepository) : BaseViewModel() {
    private lateinit var data: LiveData<Resource<Playlist>>

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return playlist()
    }

    private fun playlist(): LiveData<Resource<Playlist>> {
        data = repository.getPlayList()
        return data
    }
}