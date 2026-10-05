package com.example.easyafya

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.easyafya.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val patient = Patient()

        // Connect the Patient object to Data Binding
        binding.patient = patient

        // SAVE button
        binding.saveButton.setOnClickListener {

            val name = binding.nameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val location = binding.locationEditText.text.toString().trim()
            val healthNeed = binding.healthNeedEditText.text.toString().trim()

            // Validation
            if (
                name.isEmpty() ||
                phone.isEmpty() ||
                location.isEmpty() ||
                healthNeed.isEmpty()
            ) {

                Toast.makeText(
                    this@MainActivity,
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // Store the entered information
                patient.name = name
                patient.phone = phone
                patient.location = location
                patient.healthNeed = healthNeed

                // Refresh Data Binding
                binding.patient = patient
                binding.executePendingBindings()

                Toast.makeText(
                    this@MainActivity,
                    "Health request saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
