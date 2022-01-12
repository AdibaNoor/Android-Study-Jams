package com.example.andy


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    lateinit var user_name : TextView
    lateinit var user_email : TextView
    lateinit var user_bloodgrp : TextView
    lateinit var user_phoneno : TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var uid : String
    private lateinit var database: FirebaseDatabase

/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }*/

    private fun userProfileData() {
        //Toast.makeText(activity, "i am userprofiledata", Toast.LENGTH_SHORT).show()
        database.reference.child("Users/$uid").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                 val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    user_name.setText(user.name)
                    user_email.setText(user.email)
                    user_bloodgrp.setText(user.bloodGroup)
                    user_phoneno.setText(user.phoneNumber)

                    // Log.w("NO ERROR", "Got value $user_name")
                    //Toast.makeText(activity, "Got value $user_name", Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "Error $error", Toast.LENGTH_SHORT).show()
            }

        })
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        database = Firebase.database("https://red-saviour-c3eeb-default-rtdb.asia-southeast1.firebasedatabase.app")

            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_profile, container, false)


        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user_name = view.findViewById(R.id.user_name)
        user_email = view.findViewById(R.id.user_email)
        user_bloodgrp = view.findViewById(R.id.user_bloodgrp)
        user_phoneno = view.findViewById(R.id.user_phoneno_)
        userProfileData()

    }
}


