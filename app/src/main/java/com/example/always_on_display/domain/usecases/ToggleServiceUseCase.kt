package com.example.always_on_display.domain.usecases

import com.example.always_on_display.domain.repository.ClockSettingsRepository

class ToggleServiceUseCase(
    private val repository: ClockSettingsRepository
) {
    suspend operator fun invoke(isActivated: Boolean) {
        repository.updateServiceState(isActivated)
    }
}
