
package com.example.easyafya

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.util.Locale

object PatientRepository {

    private const val PREFS_NAME = "easyafya_patient_storage"
    private const val KEY_PATIENTS = "saved_patients"

    private val patients = mutableListOf<Patient>()

    private var initialized = false
    private lateinit var appContext: Context

    @Synchronized
    fun initialize(context: Context) {
        if (initialized) return

        appContext = context.applicationContext

        val savedJson = appContext
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_PATIENTS, null)

        if (savedJson != null) {
            try {
                val jsonArray = JSONArray(savedJson)

                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)

                    patients.add(
                        Patient(
                            name = item.optString("name", ""),
                            phone = item.optString("phone", ""),
                            location = item.optString("location", ""),
                            healthNeed = item.optString("healthNeed", "")
                        )
                    )
                }
            } catch (e: Exception) {
                patients.clear()
            }
        }

        initialized = true
    }

    private fun normalize(value: String): String {
        return value.trim().lowercase(Locale.ROOT)
    }

    private fun isDuplicate(patient: Patient): Boolean {
        return patients.any { existing ->
            normalize(existing.name) == normalize(patient.name) &&
                    normalize(existing.phone) == normalize(patient.phone) &&
                    normalize(existing.location) == normalize(patient.location) &&
                    normalize(existing.healthNeed) == normalize(patient.healthNeed)
        }
    }

    @Synchronized
    private fun persistPatients() {
        check(initialized) {
            "PatientRepository.initialize(context) must be called first."
        }

        val jsonArray = JSONArray()

        patients.forEach { patient ->
            val item = JSONObject().apply {
                put("name", patient.name)
                put("phone", patient.phone)
                put("location", patient.location)
                put("healthNeed", patient.healthNeed)
            }

            jsonArray.put(item)
        }

        appContext
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_PATIENTS, jsonArray.toString())
            .apply()
    }

    // Returns true if saved, false if already present.
    @Synchronized
    fun addPatient(patient: Patient): Boolean {
        check(initialized) {
            "PatientRepository.initialize(context) must be called first."
        }

        if (isDuplicate(patient)) {
            return false
        }

        patients.add(patient.copy())
        persistPatients()

        return true
    }

    @Synchronized
    fun getPatients(): List<Patient> {
        check(initialized) {
            "PatientRepository.initialize(context) must be called first."
        }

        return patients.map { it.copy() }
    }

    @Synchronized
    fun getPatientCount(): Int {
        check(initialized) {
            "PatientRepository.initialize(context) must be called first."
        }

        return patients.size
    }

    @Synchronized
    fun clearPatients() {
        check(initialized) {
            "PatientRepository.initialize(context) must be called first."
        }

        patients.clear()
        persistPatients()
    }
}
