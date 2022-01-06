package com.example.andy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class VerifyEmail : AppCompatActivity() {
    lateinit var verify_email : Button
    lateinit var email_input_edittext2 : TextInputEditText
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_email)

        verify_email = findViewById(R.id.verify_email)
        email_input_edittext2 = findViewById(R.id.email_input_edittext2)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        verify_email.setOnClickListener {

            val email = email_input_edittext2.text.toString().trim()

            if (email.isEmpty()) {
                email_input_edittext2.error = "Email Required"
                email_input_edittext2.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                email_input_edittext2.error = "Valid Email Required"
                email_input_edittext2.requestFocus()
                return@setOnClickListener
            }

            auth.currentUser?.sendEmailVerification()           // check in this code we don't need email_input_edittext2
                ?.addOnCompleteListener {task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Verification link sent Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, login::class.java))
                        finish()
                    }
                }?.addOnFailureListener{
                    Toast.makeText(this, "Unable to send email for verification", Toast.LENGTH_SHORT).show()
                }


        }
    }
}