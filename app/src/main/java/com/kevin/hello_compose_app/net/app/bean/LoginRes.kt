package com.kevin.hello_compose_app.net.app.bean

data class LoginRes(
    val token: String,
    val userDetail: UserDetail
)

data class UserDetail(
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val authorities: List<Any>,
    val countryCode: Any,
    val credentialsNonExpired: Boolean,
    val enabled: Boolean,
    val identities: List<Any>,
    val phone: String,
    val realName: String,
    val titleId: Int,
    val userType: String,
    val userUid: String,
    val username: String
)