package com.example.andy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView


class Onboarding1 : AppCompatActivity() {
    lateinit var next: TextView
    lateinit var skip:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding1)

        next = findViewById<Button>(R.id.next)
        skip = findViewById<Button>(R.id.skip)

    }

    override fun onStart() {
        super.onStart()
        next.setOnClickListener {
            val intent = Intent(this, Onboarding2::class.java)
            startActivity(intent)
            finish()

        }

        skip.setOnClickListener {
            val intent = Intent(this, Onboarding3::class.java)
            startActivity(intent)
            finish()
        }


    }

}