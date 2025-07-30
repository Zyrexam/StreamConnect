package com.example.streamconnect.connect

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.streamconnect.ui.theme.StreamConnectTheme

@Composable
fun ConnectScreen(
    state: ConnectState,
    onAction: (ConnectAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Choose a name",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.name,
            onValueChange = {
                onAction(ConnectAction.OnNameChange(it))
            },
            placeholder = {
                Text(text = "Name")
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onAction(ConnectAction.OnConnectClick)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Connect")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if(state.errorMessage != null) {
            Text(
                text = state.errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConnectScreenPreview() {
    StreamConnectTheme {
        ConnectScreen(
            state = ConnectState(
                errorMessage = "Hello world"
            ),
            onAction = {}
        )
    }
}

