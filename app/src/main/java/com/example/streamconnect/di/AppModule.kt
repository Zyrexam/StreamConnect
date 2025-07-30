package com.example.streamconnect.di

import com.example.streamconnect.StreamConnectApp
import com.example.streamconnect.connect.ConnectViewModel
import com.example.streamconnect.video.VideoCallViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf

val appModule = module {
    factory {
        val app = androidContext().applicationContext as StreamConnectApp
        app.client
    }

    viewModelOf(::ConnectViewModel)
    viewModelOf(::VideoCallViewModel)
}