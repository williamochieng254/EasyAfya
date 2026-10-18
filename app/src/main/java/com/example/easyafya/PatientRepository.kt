package com.example.easyafya

object PatientRepository {

    private val patients = mutableListOf<Patient>()

    // Save a health request
    fun addPatient(patient: Patient) {
        patients.add(patient)
    }

    // Retrieve all saved health requests
    fun getPatients(): List<Patient> {
        return patients.toList()
    }

    // Retrieve the number of saved health requests
    fun getPatientCount(): Int {
        return patients.size
    }

    // Clear all saved requests when needed
    fun clearPatients() {
        patients.clear()
    }
}
