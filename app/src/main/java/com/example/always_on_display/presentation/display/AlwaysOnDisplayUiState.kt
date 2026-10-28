package com.example.always_on_display.presentation.display

import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.domain.model.ClockConfig

data class AlwaysOnDisplayUiState(
    val clockConfig: ClockConfig = ClockConfig(),
    val hourInt: Int = 12,
    val minuteInt: Int = 0,
    val secondInt: Int = 0,
    val hourStr: String = "12",
    val minuteStr: String = "00",
    val secondStr: String = "00",
    val dateStr: String = "Mon, Jan 1",
    val batteryText: String = "Not Charging 100 %"
)
