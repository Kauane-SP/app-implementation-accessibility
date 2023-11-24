package com.example.projetoacessibilidade.useCase

import com.example.projetoacessibilidade.models.CardModel
import com.example.projetoacessibilidade.repository.CallbackImplementation
import com.example.projetoacessibilidade.repository.CardRepository

class CardUseCase(private val repository: CardRepository) : CallbackImplementation {

    override suspend fun requestCards(
        callbackSuccess: (successes: List<CardModel>?) -> Unit,
        callbackError: (error: String) -> Unit
    ) {
        repository.requestCards(callbackSuccess, callbackError)
    }
}