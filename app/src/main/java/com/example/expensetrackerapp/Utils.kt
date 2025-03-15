package com.example.expensetrackerapp

import java.text.SimpleDateFormat
import java.util.Locale

object Utils {

    fun formatDateToHumanReadableFrom(dateInMillis: Long): String{
        val dateFormatter = SimpleDateFormat("dd/MM/YYYY", Locale.getDefault())
        return dateFormatter.format(dateInMillis)
    }

    fun formatToDecimalValue(d: Double): String {
        return String.format("%.2f", d)
    }
}