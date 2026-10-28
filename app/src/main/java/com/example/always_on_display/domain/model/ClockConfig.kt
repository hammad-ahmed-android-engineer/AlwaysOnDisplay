package com.example.always_on_display.domain.model

/**
 * Domain Model for storing active clock configuration and service state.
 */
data class ClockConfig(
    val activeCategory: ClockCategory = ClockCategory.DIGITAL,
    val selectedDigitalStyle: String = "digital1",
    val selectedAbstractStyle: String = "abstract1",
    val selectedNeonStyle: String = "neon1",
    val selectedAtomicStyle: String = "atomic1",
    val isServiceActivated: Boolean = false
)
