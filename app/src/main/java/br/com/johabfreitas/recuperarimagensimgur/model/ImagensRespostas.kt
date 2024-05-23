package br.com.johabfreitas.recuperarimagensimgur.model

data class ImagensRespostas(
    val `data`: List<Data>,
    val status: Int,
    val success: Boolean
)