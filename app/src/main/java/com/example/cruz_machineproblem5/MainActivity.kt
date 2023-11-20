package com.example.cruz_machineproblem5

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnRegister: ImageButton
    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextUsername: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var validationFirstName: TextView
    private lateinit var validationLastName: TextView
    private lateinit var validationEmail: TextView
    private lateinit var validationUsername: TextView
    private lateinit var validationPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        btnRegister = findViewById(R.id.btnRegister)
        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPassword = findViewById(R.id.editTextPassword)
        validationFirstName = findViewById(R.id.validationFirstName)
        validationLastName = findViewById(R.id.validationLastName)
        validationEmail = findViewById(R.id.validationEmail)
        validationUsername = findViewById(R.id.validationUsername)
        validationPassword = findViewById(R.id.validationPassword)

        btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        // Get data from EditText fields
        val firstName = editTextFirstName.text.toString()
        val lastName = editTextLastName.text.toString()
        val email = editTextEmail.text.toString()
        val username = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()

        // Validate fields
        if (validateRegistrationFields(firstName, lastName, email, username, password)) {
            // Save user data to the database (you need to implement this)
            // For example, you can use a function like saveUserToDatabase(firstName, lastName, email, username, password)

            // Show a toast message
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateRegistrationFields(firstName: String, lastName: String, email: String, username: String, password: String): Boolean {
        // Validate logic (replace with your own validation rules)
        val fieldsValid = firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty()

        // Update visibility of validation messages
        validationFirstName.visibility = if (firstName.isEmpty()) View.VISIBLE else View.INVISIBLE
        validationLastName.visibility = if (lastName.isEmpty()) View.VISIBLE else View.INVISIBLE
        validationEmail.visibility = if (email.isEmpty()) View.VISIBLE else View.INVISIBLE
        validationUsername.visibility = if (username.isEmpty()) View.VISIBLE else View.INVISIBLE
        validationPassword.visibility = if (password.isEmpty()) View.VISIBLE else View.INVISIBLE

        // Password validation
        if (password.isNotEmpty()) {
            if (!isValidPassword(password)) {
                // Password doesn't meet requirements, show validation message
                validationPassword.visibility = View.VISIBLE
                validationPassword.text =
                    "Password must contain at least one small letter, one capital letter, one digit, one special character, and be at least 8 characters long."
                return false
            }
        }

        return fieldsValid
    }

    private fun isValidPassword(password: String): Boolean {
        // Password must contain at least one small letter, one capital letter, one digit, one special character, and be at least 8 characters long
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]{8,}\$"
        return password.matches(passwordRegex.toRegex())
    }
}
