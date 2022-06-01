package com.example.youtube.di

import com.example.youtube.data.repositories.DetailPlaylistRepository
import com.example.youtube.data.repositories.PlaylistRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val repoModules: Module = module {
    single { PlaylistRepository(get()) }
    single { DetailPlaylistRepository(get()) }
}