package com.example.projetoacessibilidade.useCase

import android.content.Context
import android.view.View
import com.example.projetoacessibilidade.iu.CustomDialog
import com.example.projetoacessibilidade.models.LoginModel
import com.example.projetoacessibilidade.repository.LoginRepository


class LoginUseCase(private val repository: LoginRepository) {
    suspend fun getLoginFirebase(
        loginModel: LoginModel,
        context: Context,
        view: View,
        dialog: CustomDialog,
        success: () -> Unit,
        error: () -> Unit,
    ) {
        return repository.getUsersLogin(
            loginModel,
            success,
            error,
            dialog,
            context,
            view
        )
    }
}