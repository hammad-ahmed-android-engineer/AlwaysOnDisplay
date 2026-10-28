package com.example.always_on_display.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.always_on_display.presentation.abstractclock.AbstractClockViewModel
import com.example.always_on_display.presentation.atomic.AtomicClockViewModel
import com.example.always_on_display.presentation.digital.DigitalClockViewModel
import com.example.always_on_display.presentation.display.AlwaysOnDisplayViewModel
import com.example.always_on_display.presentation.main.MainViewModel
import com.example.always_on_display.presentation.neon.NeonClockViewModel
import com.example.always_on_display.presentation.permission.PermissionViewModel
import com.example.always_on_display.presentation.splash.SplashViewModel

/**
 * Custom ViewModel Factory supplying Clean Architecture Use Cases.
 */
class ViewModelFactory(
    private val appContainer: AppContainer
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel() as T
            }
            modelClass.isAssignableFrom(PermissionViewModel::class.java) -> {
                PermissionViewModel() as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(
                    getClockSettingsUseCase = appContainer.getClockSettingsUseCase,
                    toggleServiceUseCase = appContainer.toggleServiceUseCase
                ) as T
            }
            modelClass.isAssignableFrom(DigitalClockViewModel::class.java) -> {
                DigitalClockViewModel(
                    getClockSettingsUseCase = appContainer.getClockSettingsUseCase,
                    saveClockSettingsUseCase = appContainer.saveClockSettingsUseCase
                ) as T
            }
            modelClass.isAssignableFrom(AbstractClockViewModel::class.java) -> {
                AbstractClockViewModel(
                    getClockSettingsUseCase = appContainer.getClockSettingsUseCase,
                    saveClockSettingsUseCase = appContainer.saveClockSettingsUseCase,
                    getBatteryInfoUseCase = appContainer.getBatteryInfoUseCase
                ) as T
            }
            modelClass.isAssignableFrom(NeonClockViewModel::class.java) -> {
                NeonClockViewModel(
                    getClockSettingsUseCase = appContainer.getClockSettingsUseCase,
                    saveClockSettingsUseCase = appContainer.saveClockSettingsUseCase,
                    getBatteryInfoUseCase = appContainer.getBatteryInfoUseCase
                ) as T
            }
            modelClass.isAssignableFrom(AtomicClockViewModel::class.java) -> {
                AtomicClockViewModel(
                    getClockSettingsUseCase = appContainer.getClockSettingsUseCase,
                    saveClockSettingsUseCase = appContainer.saveClockSettingsUseCase,
                    getBatteryInfoUseCase = appContainer.getBatteryInfoUseCase
                ) as T
            }
            modelClass.isAssignableFrom(AlwaysOnDisplayViewModel::class.java) -> {
                AlwaysOnDisplayViewModel(
                    getClockSettingsUseCase = appContainer.getClockSettingsUseCase,
                    getBatteryInfoUseCase = appContainer.getBatteryInfoUseCase
                ) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
