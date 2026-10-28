package com.example.always_on_display.presentation.neon

data class NeonClockUiState(
    val selectedStyle: String = "neon1",
    val hour: Int = 12,
    val minute: Int = 0,
    val second: Int = 0,
    val batteryText: String = "Not Charging 100 %"
)
