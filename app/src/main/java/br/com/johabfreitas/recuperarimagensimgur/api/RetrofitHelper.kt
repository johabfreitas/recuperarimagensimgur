package br.com.johabfreitas.recuperarimagensimgur.api

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitHelper {

    companion object {

        //const val API_KEY = ""
        const val BASE_URL = "" // gallery/search/

        /*private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.SECONDS)// Escrita (salvando na API)
            .readTimeout(20, TimeUnit.SECONDS)//Leitura (recuperando dados da API)
            .connectTimeout(20, TimeUnit.SECONDS) //Conexão máxima
            .addInterceptor(AuthInterceptor())
            .build()*/


        //Requisição para API da Imgur
        val CLIENT_ID = "e23368e01d5989d"

        //Definir a URL do servidor
        val URL = "https://api.imgur.com/3/gallery/search"

        //Criar a requisição GET
        val request = Request.Builder()
            .url(URL)
            .addHeader("Authorization", "Client-ID $CLIENT_ID")
            .get()
            .build()

        //Criar o cliente que vai disparar a requisição
        val client = OkHttpClient()

        //Enviar a requisição para o servidor Imgur
        val response = client.newCall(request).execute()

        // Extrair o body da requisição
        val responseBody = response.body()

        //Fim

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ImagesAPI::class.java)
    }
}