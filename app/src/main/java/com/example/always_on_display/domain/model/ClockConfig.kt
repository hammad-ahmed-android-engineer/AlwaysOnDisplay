package com.example.always_on_display.domain.model

/**
 * Domain Model for storing the single active selected clock configuration and service state.
 */
data class ClockConfig(
    val selectedCategory: ClockCategory = ClockCategory.DIGITAL,
    val selectedStyle: String = "digital1",
    val isServiceActivated: Boolean = false
)
