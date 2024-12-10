package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var mobileEditText: TextInputEditText
    private lateinit var otpEditText: TextInputEditText
    private lateinit var loginButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mobileEditText = findViewById(R.id.mobileEditText)
        otpEditText = findViewById(R.id.otpEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val mobile = mobileEditText.text.toString()
            if (isMobileValid(mobile)) {
                otpEditText.visibility = View.VISIBLE // Show OTP input field
                validateOtp() // Validate OTP
            } else {
                Toast.makeText(this, "Invalid mobile number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isMobileValid(mobile: String): Boolean {
        return mobile.length == 10 // Simple check for mobile number length
    }

    private fun validateOtp() {
        val otp = otpEditText.text.toString()
        if (otp.isNotEmpty()) {
            // Simulate successful OTP validation
            onLoginSuccess()
        } else {
            Toast.makeText(this, "OTP cannot be empty", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onLoginSuccess() {
        // Save the login status in SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.apply()

        // Redirect to the home page
        startActivity(Intent(this, MainActivity::class.java))
        finish() // Close LoginActivity
    }
}