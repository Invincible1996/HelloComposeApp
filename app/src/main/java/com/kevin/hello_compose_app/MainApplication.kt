package com.kevin.hello_compose_app

import android.app.Application
import com.tencent.mmkv.MMKV

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // 初始化
        MMKV.initialize(this)
    }
}