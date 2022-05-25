package com.example.youtube40.presentation.ui.activity.playlists.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube40.App
import com.example.youtube40.core.network.status.Resource
import com.example.youtube40.data.remote.dto.Playlist
import com.example.youtube40.presentation.ui.base.BaseViewModel

class DetailPlaylistViewModel : BaseViewModel() {

    fun getPlaylists(id: String): LiveData<Resource<Playlist>> {
        return playlist(id)
    }

    private fun playlist(id: String): LiveData<Resource<Playlist>> {
        var data = MutableLiveData<Resource<Playlist>>()
        data = App.detailPlaylistRepository.getPlayList(id) as MutableLiveData<Resource<Playlist>>
        return data
    }
}