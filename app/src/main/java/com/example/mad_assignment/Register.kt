package com.example.mad_assignment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mad_assignment.Model.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var storage: FirebaseStorage
    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance("https://mad-assignment-c06d5-default-rtdb.asia-southeast1.firebasedatabase.app")
        storage=FirebaseStorage.getInstance()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etFirstName = findViewById<EditText>(R.id.fname)
        val etLastName = findViewById<EditText>(R.id.lname)
        val etEmailAddress = findViewById<EditText>(R.id.email)
        val etPassword = findViewById<EditText>(R.id.retpwd)
        val btnRegister = findViewById<Button>(R.id.btnregister)

        btnRegister.setOnClickListener {
            val firstName = etFirstName.text.toString().trim()
            val lastName = etLastName.text.toString().trim()
            val email_address = etEmailAddress.text.toString().trim()
            val password= etPassword.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() || email_address.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            } else {
                // All fields are valid, go to HomeActivity
                val intent = Intent(this, home::class.java)
                startActivity(intent)
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()

                auth.createUserWithEmailAndPassword(email_address,password)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            //save user data to database
                           val user=auth.currentUser
                            user?.let {
                                val userrefrence=database.getReference("users")
                                val userid=user.uid
                                val userdata= com.example.mad_assignment.Model.UserData(firstName,lastName,email_address)
                                userrefrence.child(userid).setValue(userdata)
                                    .addOnSuccessListener{
                                        Log.d("TAG","User data saved to database")
                                    }
                                    .addOnFailureListener{ e ->
                                        Log.e("TAG","Failed to save user data to database {$e.message")
                                    }
                            }
                        }else{
                            Toast.makeText(this,"Registration failed",Toast.LENGTH_SHORT).show()
                        }
                    }
                finish()
            }
        }
    }
    }
