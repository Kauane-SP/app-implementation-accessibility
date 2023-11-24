package com.example.projetoacessibilidade.viewModel.states

import com.example.projetoacessibilidade.models.CardModel

sealed class CardTabState {
    data class GetServicesCard(val cartoes: List<CardModel>) : CardTabState()

    data class GetServicesCardError(
        val exception: String
    ) : CardTabState()
}