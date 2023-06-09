package com.kevin.hello_compose_app.net.app.bean

data class CheckUpdateRes(
    val applicationUid: String,
    val build: Int,
    val deviceType: Int,
    val forceUpdate: Int,
    val id: Int,
    val updateContent: String,
    val url: String,
    val version: String
)