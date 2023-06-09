package com.kevin.hello_compose_app.screen

import android.preference.PreferenceManager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.tencent.mmkv.MMKV

@Composable
fun SplashScreen(navController: NavHostController) {

    // 读取数据
    val mkv: MMKV = MMKV.defaultMMKV()
    val token = mkv.decodeString("token", "")

    if (!token.isNullOrBlank()) {
        navController.navigate("home") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    } else {
        navController.navigate("login") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }

    Text(text = "Welcome")
}