package com.ariefrahmanfajri.apicallingapp.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MainInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer 96891838d74af5c1987ce135f444dda82878b6a25b0911410ddcfc98e8ab5512")
            .build()
        return chain.proceed(request)

    }
}