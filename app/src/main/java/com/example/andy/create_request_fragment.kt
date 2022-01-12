package com.example.andy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast


class create_request_fragment : Fragment() {
    val types = arrayOf("A+","A-","B+","B-","AB+","AB-","O+","O-")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

    }



}
