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

    override suspend fun updateActiveCategory(category: ClockCategory) {
        dataStore.updateActiveCategory(category)
    }

    override suspend fun updateDigitalStyle(styleKey: String) {
        dataStore.updateDigitalStyle(styleKey)
    }

    override suspend fun updateAbstractStyle(styleKey: String) {
        dataStore.updateAbstractStyle(styleKey)
    }

    override suspend fun updateNeonStyle(styleKey: String) {
        dataStore.updateNeonStyle(styleKey)
    }

    override suspend fun updateAtomicStyle(styleKey: String) {
        dataStore.updateAtomicStyle(styleKey)
    }

    override suspend fun updateServiceState(isActivated: Boolean) {
        dataStore.updateServiceState(isActivated)
    }
}
