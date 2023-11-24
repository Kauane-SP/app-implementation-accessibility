package com.example.projetoacessibilidade.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projetoacessibilidade.useCase.LoginUseCase
import com.example.projetoacessibilidade.viewModel.LoginViewModel

class LoginViewModelFactory(private val useCase: LoginUseCase): ViewModelProvider.Factory {
     override fun <T : ViewModel> create(modelClass: Class<T>) = LoginViewModel(useCase) as T
}