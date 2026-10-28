package com.example.always_on_display.domain.usecases

import com.example.always_on_display.domain.model.ClockConfig
import com.example.always_on_display.domain.repository.ClockSettingsRepository
import kotlinx.coroutines.flow.Flow

class GetClockSettingsUseCase(
    private val repository: ClockSettingsRepository
) {
    operator fun invoke(): Flow<ClockConfig> {
        return repository.getClockConfig()
    }
}
