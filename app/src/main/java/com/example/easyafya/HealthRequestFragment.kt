
package com.example.easyafya

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.easyafya.databinding.FragmentHealthRequestBinding

class HealthRequestFragment : Fragment() {

    private var _binding: FragmentHealthRequestBinding? = null
    private val binding get() = _binding!!

    private var lastPatient: Patient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("EASYAFYA_LIFECYCLE", "HealthRequestFragment: onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHealthRequestBinding.inflate(
            inflater,
            container,
            false
        )

        val patient = Patient().apply {
            name = savedInstanceState?.getString("patient_name") ?: ""
            phone = savedInstanceState?.getString("patient_phone") ?: ""
            location = savedInstanceState?.getString("patient_location") ?: ""
            healthNeed = savedInstanceState?.getString("patient_health_need") ?: ""
        }

        binding.patient = patient

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val patient = binding.patient ?: Patient()

        if (
            patient.name.isNotBlank() &&
            patient.phone.isNotBlank() &&
            patient.location.isNotBlank() &&
            patient.healthNeed.isNotBlank()
        ) {
            lastPatient = patient.copy()
        }

        binding.executePendingBindings()

        binding.nameEditText.setText(patient.name)
        binding.phoneEditText.setText(patient.phone)
        binding.locationEditText.setText(patient.location)
        binding.healthNeedEditText.setText(patient.healthNeed)

        // Save health request
        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val location = binding.locationEditText.text.toString().trim()
            val healthNeed =
                binding.healthNeedEditText.text.toString().trim()

            if (
                name.isEmpty() ||
                phone.isEmpty() ||
                location.isEmpty() ||
                healthNeed.isEmpty()
            ) {
                Toast.makeText(
                    requireContext(),
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                patient.name = name
                patient.phone = phone
                patient.location = location
                patient.healthNeed = healthNeed

                lastPatient = patient.copy()

// Save the health request in the repository
                PatientRepository.addPatient(patient.copy())

                binding.patient = patient
                binding.executePendingBindings()

                Toast.makeText(
                    requireContext(),
                    "Health request saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
                Toast.makeText(
                    requireContext(),
                    "Health request saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Call contact using the phone dialer
        binding.callButton.setOnClickListener {
            val savedPatient = lastPatient

            if (savedPatient == null) {
                Toast.makeText(
                    requireContext(),
                    "Save a health request first",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val dialIntent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:${savedPatient.phone}")
                )
                startActivity(dialIntent)
            }
        }

        // Navigate to Health Information screen
        binding.healthInfoButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_healthRequestFragment_to_healthInfoFragment
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        _binding?.let { currentBinding ->
            outState.putString(
                "patient_name",
                currentBinding.nameEditText.text.toString()
            )
            outState.putString(
                "patient_phone",
                currentBinding.phoneEditText.text.toString()
            )
            outState.putString(
                "patient_location",
                currentBinding.locationEditText.text.toString()
            )
            outState.putString(
                "patient_health_need",
                currentBinding.healthNeedEditText.text.toString()
            )
        }

        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("EASYAFYA_LIFECYCLE", "HealthRequestFragment: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("EASYAFYA_LIFECYCLE", "HealthRequestFragment: onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
