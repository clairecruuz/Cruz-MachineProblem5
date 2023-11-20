package com.example.cruz_machineproblem5

// WelcomeActivity.kt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var textViewWelcome: TextView
    private lateinit var textViewUserInfo: TextView
    private lateinit var textViewUserEmail: TextView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Initialize views
        textViewWelcome = findViewById(R.id.textViewWelcome)
        textViewUserInfo = findViewById(R.id.textViewUserInfo)
        textViewUserEmail = findViewById(R.id.textViewUserEmail)
        btnLogout = findViewById(R.id.btnLogout)

        // Get user information from the intent
        val username = intent.getStringExtra("USERNAME")

        // For simplicity, you can fetch user details from the database based on the username
        val firstName = "John" // Replace with actual logic to get first name
        val lastName = "Doe"  // Replace with actual logic to get last name
        val email = "john.doe@example.com" // Replace with actual logic to get email

        // Display welcome message
        textViewWelcome.text = getString(R.string.welcome_message)
        textViewUserInfo.text = getString(R.string.welcome_user_info, firstName, lastName)
        textViewUserEmail.text = email

        // Set up logout button click listener
        btnLogout.setOnClickListener {
            // Navigate back to the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the current activity
        }
    }
}
