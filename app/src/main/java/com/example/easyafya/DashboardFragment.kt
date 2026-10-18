
package com.example.easyafya

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.easyafya.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("EASYAFYA_LIFECYCLE", "DashboardFragment: onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(
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

        binding.healthRequestButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_healthRequestFragment
            )
        }

        binding.healthInfoButton.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_healthInfoFragment
            )
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("EASYAFYA_LIFECYCLE", "DashboardFragment: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("EASYAFYA_LIFECYCLE", "DashboardFragment: onResume")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
