package com.example.youtube.di

import com.example.youtube.data.remote.RemoteDataSource
import com.example.youtube.data.remote.RetrofitClient
import org.koin.dsl.module

val networkModules = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().providePlaylistApiService() }
    factory { RemoteDataSource(get()) }
}