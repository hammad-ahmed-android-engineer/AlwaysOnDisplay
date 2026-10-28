package com.example.always_on_display.presentation.atomic

data class AtomicClockUiState(
    val selectedStyle: String = "atomic1",
    val hour: Int = 12,
    val minute: Int = 0,
    val second: Int = 0,
    val batteryText: String = "Not Charging 100 %"
)
