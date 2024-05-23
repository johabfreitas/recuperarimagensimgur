package br.com.johabfreitas.recuperarimagensimgur.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitHelper {

    companion object {

        //https://api.imgur.com/3/gallery/search/?q=cats
        const val BASE_URL = "https://api.imgur.com/3/"

        val client : OkHttpClient = OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()

        val imgurAPI by lazy {

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ImgurAPI::class.java)

        }
    }
}