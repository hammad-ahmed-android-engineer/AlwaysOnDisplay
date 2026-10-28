package com.example.always_on_display.data.repository

import com.example.always_on_display.data.datastore.ClockDataStore
import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.domain.model.ClockConfig
import com.example.always_on_display.domain.repository.ClockSettingsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [ClockSettingsRepository] wrapping DataStore operations.
 */
class ClockSettingsRepositoryImpl(
    private val dataStore: ClockDataStore
) : ClockSettingsRepository {

    override fun getClockConfig(): Flow<ClockConfig> {
        return dataStore.clockConfigFlow
    }

    override suspend fun updateSelectedClock(category: ClockCategory, styleKey: String) {
        dataStore.updateSelectedClock(category, styleKey)
    }

    override suspend fun updateServiceState(isActivated: Boolean) {
        dataStore.updateServiceState(isActivated)
    }
}
