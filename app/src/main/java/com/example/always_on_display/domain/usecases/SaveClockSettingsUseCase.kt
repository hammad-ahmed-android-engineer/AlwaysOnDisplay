package com.example.always_on_display.domain.usecases

import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.domain.repository.ClockSettingsRepository

class SaveClockSettingsUseCase(
    private val repository: ClockSettingsRepository
) {
    suspend fun selectClock(category: ClockCategory, styleKey: String) {
        repository.updateSelectedClock(category, styleKey)
    }
}
