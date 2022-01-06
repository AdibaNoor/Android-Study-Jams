package com.example.andy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class verify_screen2 : AppCompatActivity() {
    lateinit var finish : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_screen2)
        finish = findViewById(R.id.finish)
    }

    override fun onStart() {
        super.onStart()
        finish.setOnClickListener {
            startActivity(Intent(this, login::class.java))
        }
    }
}