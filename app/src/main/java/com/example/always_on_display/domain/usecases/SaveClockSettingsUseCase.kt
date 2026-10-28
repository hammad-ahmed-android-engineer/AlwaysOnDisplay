package com.example.always_on_display.domain.usecases

import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.domain.repository.ClockSettingsRepository

class SaveClockSettingsUseCase(
    private val repository: ClockSettingsRepository
) {
    suspend fun selectClockStyle(category: ClockCategory, styleKey: String) {
        repository.updateActiveCategory(category)
        when (category) {
            ClockCategory.DIGITAL -> repository.updateDigitalStyle(styleKey)
            ClockCategory.ABSTRACT -> repository.updateAbstractStyle(styleKey)
            ClockCategory.NEON -> repository.updateNeonStyle(styleKey)
            ClockCategory.ATOMIC -> repository.updateAtomicStyle(styleKey)
        }
    }
}
