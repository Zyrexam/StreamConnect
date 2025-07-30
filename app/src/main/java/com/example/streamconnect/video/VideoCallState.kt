package com.example.streamconnect.video

import io.getstream.video.android.core.Call

data class VideoCallState(
    val call: Call? = null,
    val callState: CallState = CallState.JOINING,
    val error: String? = null
)

enum class CallState {
    JOINING,
    ACTIVE,
    ENDED
}

