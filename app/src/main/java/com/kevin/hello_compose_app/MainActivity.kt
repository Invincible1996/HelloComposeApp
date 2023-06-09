package com.kevin.hello_compose_app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kevin.hello_compose_app.screen.EmailScreen
import com.kevin.hello_compose_app.screen.FaceScreen
import com.kevin.hello_compose_app.screen.FavoriteScreen
import com.kevin.hello_compose_app.screen.HomeScreen
import com.kevin.hello_compose_app.screen.LoginScreen
import com.kevin.hello_compose_app.screen.ModalNavigationDrawerSample
import com.kevin.hello_compose_app.screen.SettingScreen
import com.kevin.hello_compose_app.screen.SplashScreen
import com.kevin.hello_compose_app.view_model.LoginViewModel

class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    // At the top level of your kotlin file:
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            val context = this

            NavHost(navController = navController, startDestination = "splash") {
                composable("drawer") {
                    ModalNavigationDrawerSample()
                }
                composable("splash") {
                    SplashScreen(navController)
                }
                composable("home") {
                    HomeScreen(navController)
                }
                composable("login") {
                    LoginScreen(
                        loginViewModel = loginViewModel,
                        onLoginSuccess = { _, _ ->
                            navigateToHome(navController)
                        })
                }
                composable("email") {
                    EmailScreen()
                }
                composable("face") {
                    FaceScreen()
                }
                composable("favorite") {
                    FavoriteScreen()
                }
                composable("settings") {
                    SettingScreen(navController, context)
                }
            }
        }
    }


    private fun navigateToHome(navController: NavController) {
        navController.navigate("home") {
            popUpTo("login") {
                inclusive = true
            }
        }
    }

}

