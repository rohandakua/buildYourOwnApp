package com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions
fun getRideMessage(rideMinutes: Int): String {
    return when {
        rideMinutes <= 0 -> "Ride time not recorded. Please stay safe!"
        rideMinutes <= 60 -> "Great start! Keep going, but stay alert on the road."
        rideMinutes <= 120 -> "You've been riding for a while. Consider taking a short break!"
        rideMinutes <= 240 -> "Awesome hustle! Stretch your legs and hydrate soon."
        rideMinutes <= 360 -> "Long ride! Please rest for your safety and avoid fatigue."
        else -> "You've crossed 6 hours! It's crucial to take a proper break now."
    }
}
