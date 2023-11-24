package com.example.projetoacessibilidade.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projetoacessibilidade.useCase.CardUseCase
import com.example.projetoacessibilidade.viewModel.CardViewModel

class CardViewModelFactory(private val useCase: CardUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = CardViewModel(useCase) as T
}