package com.example.youtube40

import android.app.Application
import com.example.youtube40.data.remote.RemoteDataSource
import com.example.youtube40.data.remote.RetrofitClient
import com.example.youtube40.data.remote.apiservices.ApiService
import com.example.youtube40.data.repositories.DetailPlaylistRepository
import com.example.youtube40.data.repositories.PlaylistRepository

class App : Application() {

    companion object {
        private val dataSource by lazy {
            RemoteDataSource(apiService)
        }

        private val apiService: ApiService by lazy {
            RetrofitClient.create()
        }

        val playlistRepository: PlaylistRepository by lazy {
            PlaylistRepository(dataSource)
        }

        val detailPlaylistRepository: DetailPlaylistRepository by lazy {
            DetailPlaylistRepository(dataSource)
        }
    }
}