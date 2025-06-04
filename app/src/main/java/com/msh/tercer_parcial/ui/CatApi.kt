package com.msh.tercer_parcial.ui

import retrofit2.http.GET

interface CatApi {
    @GET("images/search?limit=20")
    suspend fun getCatImages(): List<CatImage>
}
