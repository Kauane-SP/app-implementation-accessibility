package com.example.projetoacessibilidade.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projetoacessibilidade.R
import com.example.projetoacessibilidade.fragments.Login

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_login, Login.newInstance())
            commit()
        }
    }
}