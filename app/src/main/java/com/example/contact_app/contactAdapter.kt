package com.example.contact_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class contactAdapter(private val cList:ArrayList<contact>):
    RecyclerView.Adapter<contactAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.contacts,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem= cList[position]
        holder.name.text=currentItem.name
        holder.phone.text=currentItem.phone
        holder.email.text=currentItem.email

    }

    override fun getItemCount(): Int {
        return cList.size
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name=itemView.findViewById<TextView>(R.id.name)
        val phone=itemView.findViewById<TextView>(R.id.phone)
        val email=itemView.findViewById<TextView>(R.id.email)
    }

}