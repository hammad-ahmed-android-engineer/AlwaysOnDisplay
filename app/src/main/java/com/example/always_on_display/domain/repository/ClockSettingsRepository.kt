package com.example.always_on_display.domain.repository

import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.domain.model.ClockConfig
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for clock configuration and user preferences.
 */
interface ClockSettingsRepository {
    fun getClockConfig(): Flow<ClockConfig>
    suspend fun updateActiveCategory(category: ClockCategory)
    suspend fun updateDigitalStyle(styleKey: String)
    suspend fun updateAbstractStyle(styleKey: String)
    suspend fun updateNeonStyle(styleKey: String)
    suspend fun updateAtomicStyle(styleKey: String)
    suspend fun updateServiceState(isActivated: Boolean)
}
