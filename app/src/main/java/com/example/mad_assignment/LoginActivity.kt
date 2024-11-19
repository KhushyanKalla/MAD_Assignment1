package com.example.mad_assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        //firebase intializing
        auth =FirebaseAuth.getInstance()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var guestButton = findViewById<Button>(R.id.sign)
        val etEmail = findViewById<EditText>(R.id.etemail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in both Email and Password!", Toast.LENGTH_SHORT).show()
            } else {
                // Proceed to Home Activity
                val intent = Intent(this, home::class.java)
                startActivity(intent)
                Toast.makeText(this, "Login Successful!,Welcome To Our App", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        guestButton.setOnClickListener{
            val intent = Intent(this, home::class.java)
            Toast.makeText(this, "Welcome To Our App As a Guest", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }

    }

}