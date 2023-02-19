package com.test.network.interceptor

import com.test.network.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

const val QUERY_NAME_API_KEY = "appid"

class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val httpUrl = request.url().newBuilder()
            .addQueryParameter(QUERY_NAME_API_KEY, BuildConfig.API_KEY)
            .build()
        request = request.newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}
