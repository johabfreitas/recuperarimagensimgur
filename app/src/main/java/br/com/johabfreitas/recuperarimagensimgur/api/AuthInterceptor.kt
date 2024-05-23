package br.com.johabfreitas.recuperarimagensimgur.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()

        val request = requestBuilder.addHeader("Authorization", "1ceddedc03a5d71").build()

        return chain.proceed(request)
    }
}