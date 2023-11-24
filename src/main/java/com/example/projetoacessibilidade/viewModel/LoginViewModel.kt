package com.example.projetoacessibilidade.viewModel

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetoacessibilidade.R
import com.example.projetoacessibilidade.iu.CustomDialog
import com.example.projetoacessibilidade.models.LoginModel
import com.example.projetoacessibilidade.useCase.LoginUseCase
import com.example.projetoacessibilidade.viewModel.states.LoginViewModelStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LoginViewModel(val useCase: LoginUseCase) : ViewModel() {

    private val state: MutableLiveData<LoginViewModelStates> = MutableLiveData<LoginViewModelStates>()
    val viewState: LiveData<LoginViewModelStates>
        get() = state
    private val viewModelJob = SupervisorJob()
    private val coroutineContext = Dispatchers.Main + viewModelJob

     fun getObserverLogin(
        loginModel: LoginModel,
        context: Context,
        view: View,
        dialog: CustomDialog
    ) {
        CoroutineScope(coroutineContext).launch {
            useCase.getLoginFirebase(
                loginModel,
                context,
                view,
                dialog,
                ::successLogin,
                ::errorLogin
            )
        }
    }

    private fun successLogin() {
        state.postValue(LoginViewModelStates.GetServicesFirebaseSuccess(R.string.success.toString()))
    }

    private fun errorLogin() {
        state.postValue(LoginViewModelStates.GetServicesFirebaseError(R.string.warning.toString()))
    }
}