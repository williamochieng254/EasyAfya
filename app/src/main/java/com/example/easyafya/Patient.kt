package com.example.easyafya

data class Patient(
    var name: String = "",
    var phone: String = "",
    var location: String = "",
    var healthNeed: String = "",
) {
    fun summary(): String {
        if (name.isBlank() && phone.isBlank() && location.isBlank() && healthNeed.isBlank()) {
            return ""
        }
        return "Name: $name\nPhone: $phone\nLocation: $location\nHealth Need: $healthNeed"
    }
}
