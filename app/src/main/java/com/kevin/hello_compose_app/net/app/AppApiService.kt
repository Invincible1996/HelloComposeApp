package com.kevin.hello_compose_app.net.app

import com.kevin.hello_compose_app.net.app.bean.ApiResponse
import com.kevin.hello_compose_app.net.app.bean.CheckUpdateReq
import com.kevin.hello_compose_app.net.app.bean.CheckUpdateRes
import com.kevin.hello_compose_app.net.app.bean.LoginReq
import com.kevin.hello_compose_app.net.app.bean.LoginRes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import java.net.Proxy
import java.util.concurrent.TimeUnit

private var logging = HttpLoggingInterceptor().apply {
//    if (BuildConfig.DEBUG) {
    setLevel(HttpLoggingInterceptor.Level.BODY)
//    } else {
//        setLevel(HttpLoggingInterceptor.Level.NONE)
//    }
}


private val okHttpClient: OkHttpClient =
    OkHttpClient().newBuilder().connectTimeout(5_000, TimeUnit.MILLISECONDS).addInterceptor(logging)
        .proxy(Proxy.NO_PROXY).readTimeout(5_000, TimeUnit.MILLISECONDS).build()

private var retrofit =
    Retrofit.Builder().baseUrl("https://u-portal.visioneschool.com").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

var appService: AppApiService = retrofit.create(AppApiService::class.java)

interface AppApiService {
    /**
     *
     * @param applicationUid applicationUid
     * @param deviceType deviceType
     * @desc
     */
    @POST("/v1/api/app/checkApplicationUpdate")
    suspend fun checkUpdate(@Body checkUpdateReq: CheckUpdateReq): Response<ApiResponse<CheckUpdateRes>>

    @Headers("Client: APP")
    @POST("/v1/api/auth/login")
    suspend fun login(@Body loginReq: LoginReq): Response<ApiResponse<LoginRes>>
}