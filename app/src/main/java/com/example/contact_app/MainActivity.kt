package com.example.contact_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    lateinit var list: ArrayList<users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val Email = findViewById<TextInputEditText>(R.id.emailid)
         val Password = findViewById<TextInputEditText>(R.id.passwordid)
         val Button = findViewById<Button>(R.id.button1)
         val Signup = findViewById<TextView>(R.id.accountalready)
         val AddContacts=findViewById<TextView>(R.id.addcontacts)


        Signup.setOnClickListener{
            val intent= Intent(this, signup::class.java)
            startActivity(intent)
        }
        AddContacts.setOnClickListener{
            val intent2= Intent(this,home::class.java)
            startActivity(intent2)
        }


        Button.setOnClickListener{



           list= arrayListOf()
            val ref=FirebaseDatabase.getInstance().getReference("users")
                .child(Email.text.toString()).child("details").child("password").equalTo(Password.text.toString())

            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (user in snapshot.children) {
                            val data = user.getValue(users::class.java)
                            Toast.makeText(this@MainActivity,data?.email_id.toString(),Toast.LENGTH_SHORT).show()
                            list.add(data!!)
                        }
                        val data = list[0]
                        val type = data.email_id
                        //val type2 = data.password
                        if (type == Email.text.toString()) {

                                val intent1 = Intent(this@MainActivity, home::class.java)
                                startActivity(intent1)

                        }else Toast.makeText(this@MainActivity, "email id doesn't exist", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this@MainActivity, "incorrect password", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@MainActivity, "No Data Found", Toast.LENGTH_SHORT).show()
                }
            })


        }


    }


}


