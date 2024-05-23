package br.com.johabfreitas.recuperarimagensimgur.api

import br.com.johabfreitas.recuperarimagensimgur.model.ImagensRespostas
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesAPI {


    @GET("/")
    suspend fun recuperarImagens(@Query("string") string: String ) : Response<ImagensRespostas>

}