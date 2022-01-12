package com.example.andy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class create_request : AppCompatActivity() {

    lateinit var request_btn : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_request)

        request_btn = findViewById(R.id.request_btn)
        auth = FirebaseAuth.getInstance()


    }

    override fun onStart() {
        super.onStart()

        request_btn.setOnClickListener {
            Toast.makeText(this, "request button is clicked", Toast.LENGTH_SHORT).show()

        }
    }
}