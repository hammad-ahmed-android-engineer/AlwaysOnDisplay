package com.example.always_on_display.domain.repository

import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.domain.model.ClockConfig
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for clock configuration and user preferences.
 */
interface ClockSettingsRepository {
    fun getClockConfig(): Flow<ClockConfig>
    suspend fun updateSelectedClock(category: ClockCategory, styleKey: String)
    suspend fun updateServiceState(isActivated: Boolean)
}
