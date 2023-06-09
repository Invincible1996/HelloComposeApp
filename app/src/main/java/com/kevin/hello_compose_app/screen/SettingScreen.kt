package com.kevin.hello_compose_app.screen

import android.content.Context
import android.preference.PreferenceManager
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kevin.hello_compose_app.components.CustomTopAppBar
import com.tencent.mmkv.MMKV

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController, context: Context) {

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    val itemList = listOf(
        ListItemData(Icons.Default.Info, "Version"),
        ListItemData(Icons.Default.Lock, "Privacy"),
        ListItemData(Icons.Default.Menu, "Log Out")
    )

    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Setting",
            )
        },
        content = { innerPadding ->
            Column {
                LazyColumn(
                    contentPadding = innerPadding,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(itemList.size) { index ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp),
                            color = Color.Transparent,
                            shape = RectangleShape,
                            border = BorderStroke(0.5.dp, Color.LightGray),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    imageVector = itemList[index].icon,
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = itemList[index].text
                                )
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        onClick = {
                            // Perform logout
                            showDialog.value = true
                        }
                    ) {
                        Text("Logout")
                    }
                }
            }
        })

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },
            title = {
                Text(text = "Logout")
            },
            text = {
                Text(text = "Are you sure you want to logout?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        val mkv: MMKV = MMKV.defaultMMKV()
                        mkv.removeValueForKey("token")

                        showDialog.value = false
                        navController.navigate("login") {
                            popUpTo("login") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog.value = false

                    }
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}

data class ListItemData(val icon: ImageVector, val text: String)