package com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions

fun getDistanceFeedback(distance: Double): String {
    return when {
        distance > 150 -> "You've driven over 150 km today. Please take a break for your safety!"
        distance > 100 -> "You've driven a lot today. Consider taking a short rest."
        distance in 60.0..100.0 -> "Great job! You're doing really well today."
        distance in 30.0..59.9 -> "Nice work! You're covering good ground."
        distance in 10.0..29.9 -> "Good start! Keep it going."
        distance < 10 -> "Let's get moving! More rides await you."
        else -> ""
    }
}
