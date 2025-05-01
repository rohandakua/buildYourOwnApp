package com.rohandakua.rapidopartnerhelperapp.domain.helperFunctions

import java.util.Calendar

fun getGreetings(name: String): String {
    val calendar = Calendar.getInstance()
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)

    val greeting = when {
        hourOfDay in 5..11 -> "Good morning"
        hourOfDay in 12..16 -> "Good afternoon"
        hourOfDay in 17..20 -> "Good evening"
        hourOfDay in 21..23 -> "Stay Awake"
        else -> "Rest well"
    }

    return "$greeting, $name!"
}