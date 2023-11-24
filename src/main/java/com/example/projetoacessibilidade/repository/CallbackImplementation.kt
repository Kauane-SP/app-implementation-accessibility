package com.example.projetoacessibilidade.repository

import com.example.projetoacessibilidade.models.CardModel

interface CallbackImplementation {

    suspend fun requestCards(
        callbackSuccess: (success: List<CardModel>?) -> Unit,
        callbackError: (error: String) -> Unit
    )
}