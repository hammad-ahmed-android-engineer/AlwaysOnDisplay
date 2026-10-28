package com.example.always_on_display.presentation.digital

import com.example.always_on_display.domain.model.ClockCategory

data class DigitalClockUiState(
    val selectedStyle: String = "digital1",
    val selectedCategory: ClockCategory = ClockCategory.DIGITAL,
    val hour: String = "12",
    val minute: String = "00",
    val second: String = "00",
    val dateText: String = "Mon, Jan 1",
    val batteryText: String = "Not Charging 100 %"
)
