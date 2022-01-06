package com.example.andy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ResetPassword : AppCompatActivity() {
    lateinit var email_input_edittext : TextInputEditText
    lateinit var send_btn: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        send_btn = findViewById(R.id.send_btn)
        email_input_edittext = findViewById(R.id.email_input_edittext)
        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        send_btn.setOnClickListener {
            val emailInput = email_input_edittext.text.toString().trim()


            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)


            if (emailInput.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){             // check email from the DB and verify its presence
                email_input_edittext.error = "Invalid Email Adddress"
            }
            else{
                auth.sendPasswordResetEmail(emailInput).addOnSuccessListener {
                    Toast.makeText(this, "Email link sent Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, verify_screen2::class.java))

                }
                    .addOnFailureListener {
                        Toast.makeText(this, "Unable to send verificationemail", Toast.LENGTH_SHORT).show()
                    }

            }
        }
    }
}
