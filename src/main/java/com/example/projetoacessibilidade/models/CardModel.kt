package com.example.projetoacessibilidade.models

import com.google.gson.annotations.SerializedName

data class CardModel(
    @SerializedName("number") val numberCard: String,
    @SerializedName("data") val codeCard: String,
    @SerializedName("name") val name: String,
    @SerializedName("limited_card") val limited: String,
    @SerializedName("id") val id: Int
)
