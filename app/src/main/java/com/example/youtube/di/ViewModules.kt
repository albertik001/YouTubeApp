package com.example.youtube.di

import com.example.youtube.presentation.ui.activity.playlists.PlaylistViewModel
import com.example.youtube.presentation.ui.activity.playlists.detail.DetailPlaylistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModules: Module = module {
    viewModel { PlaylistViewModel(get()) }
    viewModel { DetailPlaylistViewModel(get()) }
}