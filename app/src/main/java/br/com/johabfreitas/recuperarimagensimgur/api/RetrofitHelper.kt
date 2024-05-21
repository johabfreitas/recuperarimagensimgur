package br.com.johabfreitas.recuperarimagensimgur.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitHelper {

    companion object{

        val BASE_URL = "https://api.imgur.com/3/" // gallery/search/

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImagesAPI::class.java)
    }
}