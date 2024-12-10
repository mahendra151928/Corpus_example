package com.example.myapplication

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Ensure you have a splash screen layout

        val splashScreenView: View = findViewById(R.id.splashScreenView) // Get the splash screen view



        // After the animation ends, proceed to the next activity
        Handler().postDelayed({
            // Check login status after the animation completes
            checkLoginStatus()
        }, 5000)
    }

    private fun checkLoginStatus() {
        // Check if the user is logged in from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        // Navigate to the appropriate activity based on login status
        val nextActivity = if (isLoggedIn) MainActivity::class.java else LoginActivity::class.java
        startActivity(Intent(this, nextActivity))
        finish() // Close SplashActivity to prevent navigating back
    }
}