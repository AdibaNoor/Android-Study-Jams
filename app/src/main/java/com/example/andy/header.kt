package com.example.andy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class header : AppCompatActivity() {
    lateinit var user_name : TextView
    lateinit var user_email : TextView
    lateinit var user_bloodgrp : TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    lateinit var uid : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.header)
        user_name = findViewById(R.id.user_name)
        user_email = findViewById(R.id.user_email)
        user_bloodgrp = findViewById(R.id.user_bloodgrp)


    }

    override fun onStart() {
        super.onStart()

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
        database = Firebase.database("https://red-saviour-c3eeb-default-rtdb.asia-southeast1.firebasedatabase.app")

        headerProfile()

    }

    private fun headerProfile() {
        database.reference.child("Users/$uid").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    user_name.setText(user.name)
                    user_email.setText(user.email)
                    user_bloodgrp.setText(user.bloodGroup)

                    // Log.w("NO ERROR", "Got value $user_name")
                    //Toast.makeText(activity, "Got value $user_name", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@header, "Error $error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}