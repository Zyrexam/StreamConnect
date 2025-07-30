package com.example.streamconnect.video

sealed interface VideoCallAction {
    data object OnDisconnectClick: VideoCallAction
    data object JoinCall: VideoCallAction
}