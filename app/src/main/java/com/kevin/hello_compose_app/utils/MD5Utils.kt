package com.kevin.hello_compose_app.utils

import androidx.compose.ui.text.toUpperCase
import okhttp3.internal.and
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object MD5Utils {
    fun encrypt(password: String): String {
        try {
            // 创建MessageDigest实例并指定算法为MD5
            val md = MessageDigest.getInstance("MD5")

            // 将密码字符串转换为字节数组
            val passwordBytes = password.toByteArray()

            // 计算MD5哈希值
            val digest = md.digest(passwordBytes)

            // 将字节数组转换为十六进制字符串
            val sb = StringBuilder()
            for (b in digest) {
                sb.append(String.format("%02x", b and 0xff))
            }

            // 返回MD5加密后的密码字符串
            return sb.toString().toUpperCase()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            return ""
        }
    }
}
