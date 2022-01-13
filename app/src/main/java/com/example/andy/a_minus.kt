package com.example.andy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class a_minus : Fragment() {

    lateinit var recyclerAdapter: RecyclerAdapter
    var userArrayList: ArrayList<User> = ArrayList()
    lateinit var recyclerView: RecyclerView
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.search_donor_fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.hasFixedSize()

        database = Firebase.database("https://red-saviour-c3eeb-default-rtdb.asia-southeast1.firebasedatabase.app")
        EventChangeListener()
        recyclerAdapter = RecyclerAdapter(userArrayList)

        recyclerView.adapter = recyclerAdapter

        return view
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_a_minus, container, false)
    }

    private fun EventChangeListener() {
        database.reference.child("Users").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (datasnapshot in snapshot.children) {
                        val user = datasnapshot.getValue(User::class.java)
                        if (user != null) {
                            if (user.bloodGroup == "A-"  || user.bloodGroup == "a-") {
                                //Toast.makeText(activity, "${user.name}", Toast.LENGTH_SHORT).show()
                                userArrayList.add(
                                    User(
                                        user.userId,
                                        user.bloodGroup,
                                        user.email,
                                        user.address,
                                        user.phoneNumber,
                                        user.name
                                    )
                                )
                            }
                        }

                        recyclerAdapter.notifyDataSetChanged()
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "No Data Available", Toast.LENGTH_SHORT).show()
            }

        })
    }


}