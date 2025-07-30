package com.example.streamconnect.connect

data class ConnectState(
    val name: String = "",
    val errorMessage: String? = null,
    val isConnected: Boolean = false
)
