package com.example.andy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


class create_request_fragment : Fragment() {
    val types = arrayOf("A+","A-","B+","B-","AB+","AB-","O+","O-")
    lateinit var city : EditText
    lateinit var hospital : EditText
    lateinit var Blood_Type : EditText
    lateinit var phone_no : EditText
    lateinit var Note : EditText
    lateinit var request_btn : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var uid : String
    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        database = Firebase.database("https://red-saviour-c3eeb-default-rtdb.asia-southeast1.firebasedatabase.app")

        val t = inflater.inflate(R.layout.fragment_create_request_fragment, container, false)
        val spinner = t.findViewById<Spinner>(R.id.spinner)
        spinner?.adapter =
            activity?.applicationContext?.let { ArrayAdapter(it, R.layout.drop_down_item, types) }
        spinner?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("error")

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                Toast.makeText(activity,type, Toast.LENGTH_LONG).show()
                println(type)
            }
        }
//        val blood_type = resources.getStringArray(R.array.Blood_Type)
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item,blood_type)
//        val autoCompleteTextView = R.id.autoCompleteTextView3
//        arrayOf(autoCompleteTextView)0
        return t



        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_create_request_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        city = view.findViewById(R.id.city)
        hospital = view.findViewById(R.id.hospital)
        Blood_Type = view.findViewById(R.id.Blood_Type)
        phone_no = view.findViewById(R.id.phone_no)
        Note = view.findViewById(R.id.Note)
        request_btn = view.findViewById(R.id.request_btn)

        request_btn.setOnClickListener {
            val City = city.text.toString().trim()
            val Hospital = hospital.text.toString().trim()
            val Blood_type = Blood_Type.text.toString().trim()
            val Phone_no = phone_no.text.toString().trim()
            val note = Note.text.toString().trim()

            if (Phone_no.isEmpty() || Phone_no.length < 10) {
                phone_no.error = "Enter valid Number"
                phone_no.requestFocus()
                return@setOnClickListener
            }

            if (Blood_type.isEmpty()) {
                Blood_Type.error = "Enter Blood Group"
                Blood_Type.requestFocus()
                return@setOnClickListener
            }

            if (Hospital.isEmpty()) {
                hospital.error = "Enter Hospital"
                hospital.requestFocus()
                return@setOnClickListener
            }

            if (City.isEmpty()) {
                city.error = "Enter City"
                city.requestFocus()
                return@setOnClickListener
            }

            val donors = Donors(City, Hospital, Blood_type, Phone_no, note)
            database.reference.child("Donors").child(Blood_type).child(uid).setValue(donors)
                .addOnSuccessListener {
                    city.text.clear()
                    hospital.text.clear()
                    Blood_Type.text.clear()
                    phone_no.text.clear()
                    Note.text.clear()

                    Toast.makeText(activity, "Requested Successfully", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {

                    Toast.makeText(activity, "Faild to Request", Toast.LENGTH_SHORT).show()
                }

        }


    }


}