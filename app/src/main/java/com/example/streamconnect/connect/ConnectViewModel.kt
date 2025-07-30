package com.example.streamconnect.connect

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.streamconnect.StreamConnectApp

class ConnectViewModel(
    private val app: Application
) : AndroidViewModel(app) {

    var state by mutableStateOf(ConnectState())


    fun onAction(action: ConnectAction) {
        when (action) {
            ConnectAction.OnConnectClick -> {
                connectToRoom()
            }

            is ConnectAction.OnNameChange -> {
                state = state.copy(name = action.name)
            }
        }
    }

    private fun connectToRoom() {
        state = state.copy(errorMessage = null)
        if (state.name.isBlank()) {
            state = state.copy(
                errorMessage = "The username can't be blank."
            )
            return
        }

        //init Video Client
        (app as StreamConnectApp).initVideoClient(state.name)
        state = state.copy(isConnected = true)
    }

}