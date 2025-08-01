package com.example.streamconnect.video

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import io.getstream.video.android.compose.permission.rememberCallPermissionsState
import io.getstream.video.android.compose.ui.components.call.activecall.CallContent
import io.getstream.video.android.compose.ui.components.call.controls.actions.DefaultOnCallActionHandler
import io.getstream.video.android.core.call.state.LeaveCall

@Composable
fun VideoCallScreen(
    state: VideoCallState,
    onAction: (VideoCallAction) -> Unit,
) {
    val context = LocalContext.current

    when {
        state.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        state.callState == CallState.JOINING -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text(text = "Joining...")
            }
        }

        state.call == null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No active call",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        else -> {
            val basePermissions = listOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            )

            val bluetoothConnectPermission = if (Build.VERSION.SDK_INT >= 31) {
                listOf(Manifest.permission.BLUETOOTH_CONNECT)
            } else {
                emptyList()
            }

            val notificationPermission = if (Build.VERSION.SDK_INT >= 33) {
                listOf(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                emptyList()
            }

            // At this point, state.call is non-null due to the when condition
            val call = state.call!!

            CallContent(
                call = call,
                modifier = Modifier.fillMaxSize(),
                permissions = rememberCallPermissionsState(
                    call = call,
                    permissions = basePermissions + bluetoothConnectPermission + notificationPermission,
                    onPermissionsResult = { permissions ->
                        if (permissions.values.contains(false)) {
                            Toast.makeText(
                                context,
                                "Please grant all permissions to use this app.",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            onAction(VideoCallAction.JoinCall)
                        }
                    }
                ),
                onCallAction = { action ->
                    if (action == LeaveCall) {
                        onAction(VideoCallAction.OnDisconnectClick)
                    }
                    DefaultOnCallActionHandler.onCallAction(call, action)
                },
                onBackPressed = {
                    onAction(VideoCallAction.OnDisconnectClick)
                }
            )
        }
    }
}
