package com.example.always_on_display.presentation.digital

data class DigitalClockUiState(
    val selectedStyle: String = "digital1",
    val hour: String = "12",
    val minute: String = "00",
    val second: String = "00",
    val dateText: String = "Mon, Jan 1",
    val batteryText: String = "Not Charging 100 %"
)
