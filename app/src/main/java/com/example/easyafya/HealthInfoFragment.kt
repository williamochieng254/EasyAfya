
package com.example.easyafya

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.easyafya.databinding.FragmentHealthInfoBinding

class HealthInfoFragment : Fragment() {

    private var _binding: FragmentHealthInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("EASYAFYA_LIFECYCLE", "HealthInfoFragment: onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHealthInfoBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        displaySavedRequests()

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun displaySavedRequests() {
        val patients = PatientRepository.getPatients()

        binding.savedRequestsContainer.removeAllViews()

        if (patients.isEmpty()) {
            val emptyMessage = TextView(requireContext()).apply {
                text = "No health requests have been saved yet."
                textSize = 16f
                setPadding(0, 16, 0, 16)
            }

            binding.savedRequestsContainer.addView(emptyMessage)
            return
        }

        patients.forEachIndexed { index, patient ->
            val requestView = TextView(requireContext()).apply {
                text = "Health Request ${index + 1}\n${patient.summary()}"
                textSize = 16f
                setPadding(12, 16, 12, 16)
            }

            binding.savedRequestsContainer.addView(requestView)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("EASYAFYA_LIFECYCLE", "HealthInfoFragment: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("EASYAFYA_LIFECYCLE", "HealthInfoFragment: onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
