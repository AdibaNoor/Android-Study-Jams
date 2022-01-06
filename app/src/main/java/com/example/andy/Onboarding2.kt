package com.example.andy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Onboarding2 : AppCompatActivity() {
    lateinit var next2: TextView
    lateinit var skip2: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding2)

        next2 = findViewById<Button>(R.id.next2)
        skip2 = findViewById<Button>(R.id.skip2)

    }

    override fun onStart() {
        super.onStart()
        next2.setOnClickListener {
            val intent = Intent(this, Onboarding3::class.java)
            startActivity(intent)
            finish()
        }

        skip2.setOnClickListener {
            val intent = Intent(this, Onboarding1::class.java)
            startActivity(intent)
            finish()
        }

    }
}