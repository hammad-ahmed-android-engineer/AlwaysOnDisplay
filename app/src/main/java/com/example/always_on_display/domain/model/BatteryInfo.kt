package com.example.always_on_display.domain.model

/**
 * Domain Model for battery status.
 */
data class BatteryInfo(
    val percentage: Int = 0,
    val statusText: String = "Not Charging"
)
