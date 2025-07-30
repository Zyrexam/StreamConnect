package com.example.streamconnect.connect

sealed class ConnectAction {
    data class OnNameChange(val name: String) : ConnectAction()
    object OnConnectClick : ConnectAction()
}