package com.example.andy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation


class Homefragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var navController = Navigation.findNavController(view)
       val card1 = view.findViewById<CardView>(R.id.card1)
       card1.setOnClickListener {
           Toast.makeText(activity, "This is the Home Page ", Toast.LENGTH_SHORT).show()
        }
        val card2 = view.findViewById<CardView>(R.id.card2)
        card2.setOnClickListener {
            navController.navigate(R.id.action_homefragment_to_create_request_fragment)
        }
        val card3 = view.findViewById<CardView>(R.id.card3)
        card3.setOnClickListener {
            navController.navigate(R.id.action_homefragment_to_profileFragment)
        }
        val card4 = view.findViewById<CardView>(R.id.card4)
        card4.setOnClickListener {
            navController.navigate(R.id.action_homefragment_to_sendEmailfragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homefragment, container, false)
    }


}