package com.example.andy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Onboarding3 : AppCompatActivity() {
    lateinit var login: Button
    lateinit var register:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding3)

        login = findViewById<Button>(R.id.login_onboarding)
        register = findViewById<Button>(R.id.register)
    }

    override fun onStart() {
        super.onStart()
        login.setOnClickListener {
            val intent = Intent(this, com.example.andy.login::class.java)
            startActivity(intent)
        }

        register.setOnClickListener {
            val intent = Intent(this, RegScreen::class.java)
            startActivity(intent)
        }


    }
}