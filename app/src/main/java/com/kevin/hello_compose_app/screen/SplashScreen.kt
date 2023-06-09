package com.kevin.hello_compose_app.screen

import android.preference.PreferenceManager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    val token = sharedPreferences.getString("token", "")
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