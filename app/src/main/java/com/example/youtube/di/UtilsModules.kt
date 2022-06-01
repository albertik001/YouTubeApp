package com.example.youtube.di

import com.example.youtube.core.utils.ConnectivityStatus
import org.koin.core.module.Module
import org.koin.dsl.module

val utilsModules: Module = module {
    single { ConnectivityStatus(get()) }
}