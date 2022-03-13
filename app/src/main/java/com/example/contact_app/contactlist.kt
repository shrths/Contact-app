package com.example.contact_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class contactlist : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var recycle: RecyclerView
    private lateinit var userarray: ArrayList<contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactlist)
        recycle=findViewById(R.id.recyclerview)
        recycle.layoutManager= LinearLayoutManager(this)
        recycle.setHasFixedSize(true)
        userarray= arrayListOf()
        getcontacts()
    }
    private fun getcontacts() {
        dbref= FirebaseDatabase.getInstance().getReference("contact")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (csnapshots in snapshot.children){
                        val contactss= csnapshots.getValue(contact::class.java)
                        userarray.add(contactss!!)
                    }
                    recycle.adapter=contactAdapter(userarray)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}