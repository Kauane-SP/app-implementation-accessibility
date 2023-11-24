package com.example.projetoacessibilidade.repository

import android.content.Context
import android.view.View
import com.example.projetoacessibilidade.api.LoginApi
import com.example.projetoacessibilidade.iu.CustomDialog
import com.example.projetoacessibilidade.models.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(val api: LoginApi) {
    suspend fun getUsersLogin(
        loginModel: LoginModel,
        success: () -> Unit,
        error: () -> Unit,
        dialog: CustomDialog,
        context: Context,
        view: View
    ) {
        withContext(Dispatchers.IO){
            api.requestLogin(
                loginModel,
                success,
                error,
                dialog,
                context,
                view
            )
        }
    }
}