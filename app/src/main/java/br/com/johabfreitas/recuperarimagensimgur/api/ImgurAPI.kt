package br.com.johabfreitas.recuperarimagensimgur.api

import br.com.johabfreitas.recuperarimagensimgur.model.ImagensRespostas
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurAPI {

    @GET("gallery/search/")
    suspend fun recuperarImagensGaleria(@Query("q") q: String ) : Response<ImagensRespostas>

}