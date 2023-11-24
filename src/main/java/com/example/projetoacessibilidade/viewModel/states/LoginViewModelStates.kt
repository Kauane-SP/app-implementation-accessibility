package com.example.projetoacessibilidade.viewModel.states

sealed class LoginViewModelStates() {
    class GetServicesFirebaseError(
        val error: String
    ): LoginViewModelStates()

    class GetServicesFirebaseSuccess(
        val success: String
    ): LoginViewModelStates()
}
