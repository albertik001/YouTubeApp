package com.example.youtube40.ui.activity.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube40.BuildConfig.API_KEY
import com.example.youtube40.base.BaseViewModel
import com.example.youtube40.common.`object`.Constant
import com.example.youtube40.model.Playlist
import com.example.youtube40.remote.ApiService
import com.example.youtube40.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaylistViewModel : BaseViewModel() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    fun getPlaylists(): LiveData<Playlist> {
        return playlist()
    }

    private fun playlist(): LiveData<Playlist> {
        val data = MutableLiveData<Playlist>()
        apiService.getPlaylists(Constant.part, API_KEY, Constant.channelId).enqueue(object :
            Callback<Playlist> {
            override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<Playlist>, t: Throwable) {
                // 404 - не найдено 401 - токен истек, 403 - нет доступа
                print(t.stackTrace)
            }
        })
        return data
    }
}