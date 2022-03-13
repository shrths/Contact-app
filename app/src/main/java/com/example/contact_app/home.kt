package com.example.contact_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.*


class home : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val Name = findViewById<TextInputEditText>(R.id.name)
        val Phone = findViewById<TextInputEditText>(R.id.phone)
        val Email = findViewById<TextInputEditText>(R.id.emails)
        val Save = findViewById<Button>(R.id.Buttonsubmit)
        val COntacts=findViewById<Button>(R.id.Buttoncontact)
        Save.setOnClickListener {

        val dbref: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("contact").child(Name.text.toString())
        if (Email.text.toString().isBlank() || Phone.text.toString()
                .isBlank() || Name.text.toString().isBlank()
        ) {
            Toast.makeText(this, "Name, Phone and Email should not be empty", Toast.LENGTH_SHORT)
                .show()
        } else {
            dbref.child("name").setValue(Name.text.toString())
            dbref.child("phone").setValue(Phone.text.toString())
            dbref.child("email").setValue(Email.text.toString())
            Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()

        }
    }
        COntacts.setOnClickListener{
            val intent =Intent(this,contactlist::class.java)
            startActivity(intent)
        }
    }
}