package com.example.always_on_display.di

import android.content.Context
import com.example.always_on_display.data.datastore.ClockDataStore
import com.example.always_on_display.data.repository.BatteryRepositoryImpl
import com.example.always_on_display.data.repository.ClockSettingsRepositoryImpl
import com.example.always_on_display.domain.repository.BatteryRepository
import com.example.always_on_display.domain.repository.ClockSettingsRepository
import com.example.always_on_display.domain.usecases.GetBatteryInfoUseCase
import com.example.always_on_display.domain.usecases.GetClockSettingsUseCase
import com.example.always_on_display.domain.usecases.SaveClockSettingsUseCase
import com.example.always_on_display.domain.usecases.ToggleServiceUseCase

/**
 * Clean Architecture Dependency Injection Container.
 */
class AppContainer(private val context: Context) {

    private val clockDataStore by lazy {
        ClockDataStore(context)
    }

    val clockSettingsRepository: ClockSettingsRepository by lazy {
        ClockSettingsRepositoryImpl(clockDataStore)
    }

    val batteryRepository: BatteryRepository by lazy {
        BatteryRepositoryImpl(context)
    }

    val getClockSettingsUseCase by lazy {
        GetClockSettingsUseCase(clockSettingsRepository)
    }

    val saveClockSettingsUseCase by lazy {
        SaveClockSettingsUseCase(clockSettingsRepository)
    }

    val getBatteryInfoUseCase by lazy {
        GetBatteryInfoUseCase(batteryRepository)
    }

    val toggleServiceUseCase by lazy {
        ToggleServiceUseCase(clockSettingsRepository)
    }
}
