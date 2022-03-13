package com.example.contact_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val Email= findViewById<TextInputEditText>(R.id.emailid)
        val Password= findViewById<TextInputEditText>(R.id.passwordid)
        val Password1= findViewById<TextInputEditText>(R.id.passwordid1)
        val Button= findViewById<Button>(R.id.button1)
        val Account= findViewById<TextView>(R.id.accountalready)
        Account.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        Button.setOnClickListener {

            val dbref: DatabaseReference =
                FirebaseDatabase.getInstance().getReference("users").child(Email.text.toString()).child("details")


            if (Password.text.toString() == Password1.text.toString()) {
                dbref.child("email_id").setValue(Email.text.toString())
                dbref.child("password").setValue(Password.text.toString())
                val intent1= Intent(this,MainActivity::class.java)
                startActivity(intent1)
            }
            else {
                Toast.makeText(
                    this,
                    "Passwords doesn't match retype the password",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }

    }
}


