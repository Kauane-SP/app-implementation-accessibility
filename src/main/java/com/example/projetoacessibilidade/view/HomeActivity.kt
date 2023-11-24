package com.example.projetoacessibilidade.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projetoacessibilidade.R
import com.example.projetoacessibilidade.fragments.Home
import com.example.projetoacessibilidade.fragments.Login

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content, Home.newInstance())
            commit()
        }
    }
}