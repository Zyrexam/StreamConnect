package com.example.streamconnect

import android.app.Application
import com.example.streamconnect.di.appModule
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User
import io.getstream.video.android.model.UserType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class StreamConnectApp : Application() {
    private var currentName: String? = null
    var client: StreamVideo? = null

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@StreamConnectApp)
            modules(appModule)
        }
    }

    fun initVideoClient(username: String) {
        if (client == null || username != currentName) {
            StreamVideo.removeClient()
            currentName = username

            client = StreamVideoBuilder(
                context = this,
                apiKey = "48tz4amunet4",
                user = User(
                    id = username,
                    name = username,
                    type = UserType.Guest
                )
            ).build()
        }
    }

    companion object {
        private const val TAG = "StreamConnectApp"
    }
} 