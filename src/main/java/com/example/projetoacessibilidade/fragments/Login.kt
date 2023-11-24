package com.example.projetoacessibilidade.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.example.projetoacessibilidade.R
import com.example.projetoacessibilidade.api.LoginApi
import com.example.projetoacessibilidade.iu.CustomDialog
import com.example.projetoacessibilidade.models.LoginModel
import com.example.projetoacessibilidade.repository.LoginRepository
import com.example.projetoacessibilidade.useCase.AccessibilityCommons
import com.example.projetoacessibilidade.useCase.LoginUseCase
import com.example.projetoacessibilidade.view.HomeActivity
import com.example.projetoacessibilidade.viewModel.LoginViewModel
import com.example.projetoacessibilidade.viewModelFactory.LoginViewModelFactory
import com.example.projetoacessibilidade.viewModel.states.LoginViewModelStates

class Login : Fragment() {

    private lateinit var tvCreateNewUser: TextView
    private lateinit var viewModel: LoginViewModel
    private lateinit var api: LoginApi
    private lateinit var dialog: CustomDialog
    private lateinit var buttonConfirmLogin: AppCompatButton
    private lateinit var loginModel: LoginModel
    private lateinit var etLoginEmail: EditText
    private lateinit var etLoginPassword: EditText
    private lateinit var tvTitleLogin: TextView
    private lateinit var createAccount: TextView
    private lateinit var digitalButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = activity as Context
        dialog = CustomDialog()
        initViews(view)
        initViewModel()
        navigateRegistrationScream()
        setOnClickBottomLogin(context, view)
        setAccessibility()
    }

    private fun initViews(view: View) {
        buttonConfirmLogin = view.findViewById(R.id.button_login_confirmation)
        tvCreateNewUser = view.findViewById(R.id.text_create_login)
        etLoginEmail = view.findViewById(R.id.ed_text_email_login)
        etLoginPassword = view.findViewById(R.id.ed_text_register_login)
        tvTitleLogin = view.findViewById(R.id.title_login)
        createAccount = view.findViewById(R.id.text_create_login)
        digitalButton = view.findViewById(R.id.button_fingerprint)
    }

    private fun setAccessibility() {
        AccessibilityCommons.addTitleSemantics(tvTitleLogin)
        AccessibilityCommons.accessibilityButton(createAccount)
        AccessibilityCommons.accessibilityButton(digitalButton)
        AccessibilityCommons.setViewContentDescription(digitalButton, "acesso com digital")
    }

    private fun navigateRegistrationScream() {
        tvCreateNewUser.setOnClickListener {
            loadFragmentB()
        }
    }

    private fun setOnClickBottomLogin(context: Context, view: View) {
        buttonConfirmLogin.setOnClickListener {
            loginModel = LoginModel(etLoginPassword.text.toString(), etLoginEmail.text.toString(),)
            observerViewModel(context, view, loginModel)
        }
    }

    private fun loadFragmentB() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.content_login, Cadastro())
            .addToBackStack(null)
            .commit()
    }

    private fun initViewModel() {
        api = LoginApi()
        val repository = LoginRepository(api)
        val useCase = LoginUseCase(repository)
        viewModel = ViewModelProvider(
            this, LoginViewModelFactory(useCase)
        ).get(LoginViewModel::class.java)
    }

    private fun observerViewModel(context: Context, view: View, loginModel: LoginModel) {
        viewModel.getObserverLogin(loginModel, context, view, dialog)
        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is LoginViewModelStates.GetServicesFirebaseSuccess -> {
                    successCallBack()
                }

                is LoginViewModelStates.GetServicesFirebaseError -> {
                    errorCallBack()
                }
            }
        }
    }

    private fun successCallBack() {
        val intent = Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
    }

    private fun errorCallBack() {
        Toast.makeText(context, getText(R.string.warning), Toast.LENGTH_SHORT)
    }

    companion object {
        fun newInstance() = Login()
    }
}