package com.example.cruz_machineproblem5

// LoginActivity.kt

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin: ImageButton
    private lateinit var btnLogRegister: ImageButton
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var validationUsername: TextView
    private lateinit var validationPassword: TextView
    private lateinit var databaseHandler: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        btnLogin = findViewById(R.id.btnLogin)
        btnLogRegister = findViewById(R.id.btnLog_Register)
        editTextUsername = findViewById(R.id.editTextUsername_Login)
        editTextPassword = findViewById(R.id.editTextPassword_Login)
        validationUsername = findViewById(R.id.validationUsername_Login)
        validationPassword = findViewById(R.id.validationPassword_Login)

        databaseHandler = DatabaseHandler(this)

        btnLogin.setOnClickListener {
            loginUser()
        }

        btnLogRegister.setOnClickListener {
            // Navigate to the registration page
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun loginUser() {
        val username = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()

        if (validateLoginFields(username, password)) {
            if (databaseHandler.checkUser(username, password)) {
                // Login successful, navigate to the welcome page
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateLoginFields(username: String, password: String): Boolean {
        // Validate logic (replace with your own validation rules)
        val fieldsValid = username.isNotEmpty() && password.isNotEmpty()

        // Update visibility of validation messages
        validationUsername.visibility = if (username.isEmpty()) View.VISIBLE else View.INVISIBLE
        validationPassword.visibility = if (password.isEmpty()) View.VISIBLE else View.INVISIBLE

        return fieldsValid
    }
}
