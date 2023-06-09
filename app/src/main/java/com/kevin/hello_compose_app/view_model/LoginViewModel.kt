package com.kevin.hello_compose_app.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kevin.hello_compose_app.net.app.appService
import com.kevin.hello_compose_app.net.app.bean.LoginReq
import com.kevin.hello_compose_app.utils.MD5Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    private val _isLoggedIn = mutableStateOf(false)
//    val isLoggedIn: State<Boolean> = _isLoggedIn

    fun login(
        username: String,
        password: String,
        onLoginResult: (isSuccess: Boolean, token: String) -> Unit
    ) {

        val encryptedPassword = MD5Utils.encrypt(password)


        viewModelScope.launch(Dispatchers.IO) {
            try {
                // 登录
                val loginReq = LoginReq(username, encryptedPassword, "Employee")
                val response = appService.login(loginReq)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    withContext(Dispatchers.Main) {
                        // 登录成功处理
                        println("登录成功：${loginResponse?.data?.token}")

                        //保存token到本地
                        // Get the SharedPreferences object.


                        onLoginResult(true, loginResponse?.data?.token!!)
                        _isLoggedIn.value = true
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        // 登录失败处理
                        println("登录失败：${response.code()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    // 异常处理
                    println("登录异常：${e.message}")
                }
            }
        }

    }
}
