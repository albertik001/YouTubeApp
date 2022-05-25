package com.example.youtube40.presentation.ui.activity.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube40.App
import com.example.youtube40.core.network.status.Resource
import com.example.youtube40.data.remote.dto.Playlist
import com.example.youtube40.presentation.ui.base.BaseViewModel

class PlaylistViewModel : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return playlist()
    }

    private fun playlist(): LiveData<Resource<Playlist>> {
        var data = MutableLiveData<Resource<Playlist>>()
        data = App.playlistRepository.getPlayList() as MutableLiveData<Resource<Playlist>>
        return data
    }
}