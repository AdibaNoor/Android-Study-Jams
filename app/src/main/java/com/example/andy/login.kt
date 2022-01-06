package com.example.andy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var Register_now: TextView
    lateinit var Forgetpss: TextView
    lateinit var Login: Button
    lateinit var EmailAddress:EditText
    lateinit var Password :EditText
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        auth = FirebaseAuth.getInstance()
        Register_now = findViewById(R.id.Register_Now)
        Forgetpss = findViewById(R.id.Forgetpss)
        Login = findViewById(R.id.login)
        EmailAddress  = findViewById(R.id.EmailAddress)
        Password = findViewById(R.id.Password)

    }

    override fun onStart() {
        super.onStart()

        val emailaddr = EmailAddress.text.toString().trim()
        val paswd = Password.text.toString().trim()

        Register_now.setOnClickListener {
            startActivity(Intent(this, RegScreen::class.java))
        }

        Forgetpss.setOnClickListener {
            //Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ResetPassword::class.java))             //bug2- unable to redirect in Reset_Password
        }

        Login.setOnClickListener {

            checkUser()

        }

    }

    private fun checkUser(){
        val firebaseUser = auth.currentUser
        if (firebaseUser != null){                      // bug1 - able to login without user data and check valid user data (SOLVED)
            validateData()
            finish()
        }

    }


    private fun validateData(){
        email = EmailAddress.text.toString().trim()
        password = Password.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EmailAddress.error = "Invalid Email Address"
            EmailAddress.requestFocus()
        }
        else if (TextUtils.isEmpty(password)){
            Password.error = "Please Enter Password"
        }
        else if (password.length < 6){
            Password.error = "Password must be atleast 6 character long"
        }
        else{
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val firebaseUser = auth.currentUser
                val email = firebaseUser!!.email
                startActivity(Intent(this,homepage::class.java))                //bug 4- app get closed before login
                Toast.makeText(this, "Loged In as $email", Toast.LENGTH_SHORT).show()

                //open User profile by intent

            }
            .addOnFailureListener {
                Toast.makeText(this,"Login failed", Toast.LENGTH_SHORT).show()
            }
    }

}

// bug 3 - moving to onboarding 3 on pressing back