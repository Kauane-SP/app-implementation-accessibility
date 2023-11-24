package com.example.projetoacessibilidade.api

import com.example.projetoacessibilidade.models.CardModel
import retrofit2.Call
import retrofit2.http.GET

interface CardApi {
    @GET("cartoes")
    fun getCartoesApi(
    ): Call<List<CardModel>>
}