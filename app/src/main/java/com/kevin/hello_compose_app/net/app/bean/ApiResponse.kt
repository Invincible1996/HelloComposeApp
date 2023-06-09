package com.kevin.hello_compose_app.net.app.bean

data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T?
)

