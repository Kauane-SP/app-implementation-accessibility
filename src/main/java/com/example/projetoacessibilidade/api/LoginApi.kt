package com.example.projetoacessibilidade.api

import android.content.Context
import android.view.View
import com.example.projetoacessibilidade.R
import com.example.projetoacessibilidade.iu.CustomDialog
import com.example.projetoacessibilidade.models.LoginModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginApi() {

    fun requestLogin(
        loginModel: LoginModel,
        success: () -> Unit,
        error: () -> Unit,
        dialog: CustomDialog,
        context: Context,
        view: View
    ) {
        while (loginModel.email.isNotEmpty() && loginModel.register.isNotEmpty()) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(loginModel.email, loginModel.register)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        success.invoke()
                    } else {
                        error.invoke()
                    }
                }
                .addOnFailureListener {
                    when (it) {
                        is FirebaseAuthWeakPasswordException -> {
                            error.invoke()
                            dialog.dialogInstance(
                                view,
                                context.getString(R.string.warning),
                                context.getString(R.string.register_failed)
                            )
                        }
                        else -> {
                            error.invoke()
                            dialog.dialogInstance(
                                view,
                                context.getString(R.string.warning),
                                context.getString(R.string.warning_simple)
                            )
                        }
                    }

                }
        }
    }
}