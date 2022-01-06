package com.example.andy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val userList : ArrayList<User>) :RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val currentitem = userList[position]
        holder.name.text = currentitem.name
        holder.email.text = currentitem.email
        holder.bloodgrp.text = currentitem.bloodGroup
        //holder.cardImage.imageAlpha = currentitem.cardImage
    }

    override fun getItemCount(): Int {
       return userList.size
    }
    inner class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var cardImage: ImageView
        var name: TextView
        var email: TextView
        var bloodgrp: TextView
        init {
            cardImage = itemView.findViewById(R.id.card_image)
            name = itemView.findViewById(R.id.card_text1)
            email = itemView.findViewById(R.id.card_text2)
            bloodgrp = itemView.findViewById(R.id.card_text3)


        }
    }

}