package com.example.projetoacessibilidade.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.example.projetoacessibilidade.R
import com.example.projetoacessibilidade.useCase.AccessibilityCommons
import com.example.projetoacessibilidade.view.HomeActivity
import com.google.firebase.auth.FirebaseAuth

class Cadastro : Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btConfirm: AppCompatButton
    private lateinit var tvTitleForms: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvName: TextView
    private lateinit var tvRg: TextView
    private lateinit var tvPassword: TextView
    private lateinit var etName: EditText
    private lateinit var etRg: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cadastro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setConfirmationButton()
        setAccessibility()
    }

    private fun initView(view: View) {
        etEmail = view.findViewById(R.id.ed_text_register_login)
        etPassword = view.findViewById(R.id.ed_text_register_senha)
        btConfirm = view.findViewById(R.id.button_cadastro_confirmation)
        tvTitleForms = view.findViewById(R.id.title_cadastro)
        tvEmail = view.findViewById(R.id.text_email)
        tvName = view.findViewById(R.id.text_name)
        tvRg = view.findViewById(R.id.text_documento)
        tvPassword = view.findViewById(R.id.text_senha)
        etName = view.findViewById(R.id.ed_text_register_name)
        etRg = view.findViewById(R.id.ed_text_register_documento)
    }

    private fun setAccessibility() {
        AccessibilityCommons.addTitleSemantics(tvTitleForms)
        AccessibilityCommons.addTitleSemantics(tvEmail)
        AccessibilityCommons.addTitleSemantics(tvName)
        AccessibilityCommons.addTitleSemantics(tvRg)
        AccessibilityCommons.addTitleSemantics(tvPassword)

        AccessibilityCommons.setViewContentDescription(etEmail,"Digite seu email")
        AccessibilityCommons.setViewContentDescription(etName,"Digite seu nome")
        AccessibilityCommons.setViewContentDescription(etRg,"Digite seu Rg")
        AccessibilityCommons.setViewContentDescription(etPassword,"Digite uma senha")
    }
    private fun setConfirmationButton() {
        btConfirm.setOnClickListener {
            registerUser()
        }
    }


    private fun registerUser() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, getText(R.string.warning), Toast.LENGTH_SHORT).show()
        } else {
            validationRegisterUsers(email, password)
        }
    }

    private fun validationRegisterUsers(email: String, senha: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        context,
                        getText(R.string.confirmation_success),
                        Toast.LENGTH_SHORT
                    ).show()
                    startHome()
                } else {
                    Toast.makeText(
                        context,
                        getText(R.string.confirmation_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun startHome() {
        val intent = Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
    }
}