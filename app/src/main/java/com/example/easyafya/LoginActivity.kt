package com.example.easyafya

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val emailEditText =
            findViewById<EditText>(R.id.emailEditText)

        val passwordEditText =
            findViewById<EditText>(R.id.passwordEditText)

        val loginButton =
            findViewById<Button>(R.id.loginButton)

        val registerTextView =
            findViewById<TextView>(R.id.registerTextView)

        val helpTextView =
            findViewById<TextView>(R.id.helpTextView)

        // Get the email sent from RegisterActivity
        val registeredEmail = intent.getStringExtra("EMAIL")

        if (registeredEmail != null) {
            emailEditText.setText(registeredEmail)
        }

        // LOGIN button
        loginButton.setOnClickListener {

            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter your email and password",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Open Register screen
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Open Strathmore University website using an implicit Intent
        helpTextView.setOnClickListener {
            val websiteIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.strathmore.edu")
            )

            try {
                startActivity(websiteIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "No browser is available to open the website",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
