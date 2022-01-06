package com.example.andy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class verify_screen1 : AppCompatActivity() {
    public lateinit var verify_btn : Button
    public lateinit var resend_code : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_screen1)

        verify_btn = findViewById(R.id.verify_btn)
        resend_code = findViewById(R.id.resend_codes)
    }

    override fun onStart() {
        super.onStart()
        verify_btn.setOnClickListener {
            var intent = Intent(this, verify_screen2::class.java)
            startActivity(intent)
        }
    }
}
