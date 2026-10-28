package com.example.always_on_display.domain.model

/**
 * Domain Enum representing clock categories.
 */
enum class ClockCategory {
    DIGITAL,
    ABSTRACT,
    NEON,
    ATOMIC
}

/**
 * Domain Model for clock type representation.
 */
data class ClockType(
    val category: ClockCategory,
    val styleKey: String
)
