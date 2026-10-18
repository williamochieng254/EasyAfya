package com.example.easyafya

import java.io.Serializable

data class Patient(
    var name: String = "",
    var phone: String = "",
    var location: String = "",
    var healthNeed: String = ""
) : Serializable {

    fun summary(): String {
        if (
            name.isBlank() &&
            phone.isBlank() &&
            location.isBlank() &&
            healthNeed.isBlank()
        ) {
            return ""
        }

        return "Name: $name\n" +
                "Phone: $phone\n" +
                "Location: $location\n" +
                "Health Need: $healthNeed"
    }
}
