package com.kevin.hello_compose_app.screen

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SettingScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Version")
        }
        item {
            Text("Privacy")
        }
        item {
            Button(onClick = {
                // Log out
            }) {
                Text("Log Out")
            }
        }
    }
}