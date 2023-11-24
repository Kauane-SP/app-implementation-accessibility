package com.example.projetoacessibilidade.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CardApiTask {

    private fun getUrlProvider(): Retrofit = Retrofit.Builder()
        .baseUrl("https://610aee6952d56400176b0079.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun cartoesApi(): CardApi = getUrlProvider().create()
}