package com.kevin.hello_compose_app.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kevin.hello_compose_app.view_model.LoginViewModel

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onLoginSuccess: (success: Boolean, token: String) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = MutableInteractionSource()
    val focusManager = LocalFocusManager.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisibilityState = remember { mutableStateOf(false) }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(interactionSource = interactionSource, indication = null, onClick = {
                // 点击空白处时隐藏键盘
                keyboardController?.hide()
                focusManager.clearFocus()
            }), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = "https://bigshot.oss-cn-shanghai.aliyuncs.com/nba/bos.png",
                contentDescription = null,
            )
            OutlinedTextField(
                label = { Text("Username") },
                value = username,
                onValueChange = { username = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(label = { Text("Password") },
                value = password,
                onValueChange = { password = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisibilityState.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibilityState.value = !passwordVisibilityState.value
                    }) {
                        val visibilityIcon: ImageVector =
                            if (passwordVisibilityState.value) Icons.Default.Home else Icons.Default.Close
                        Icon(
                            imageVector = visibilityIcon,
                            contentDescription = "Toggle Password Visibility"
                        )
                    }
                })
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // 启动一个新的作用域
                    loginViewModel.login(username, password) { success, token ->
                        if (success) {
                            onLoginSuccess(success, token)
                            Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show()
                        }
                    }
                }, modifier = Modifier.widthIn(min = TextFieldDefaults.MinWidth)
            ) {
                Text("Login")
            }
        }
    }
}
