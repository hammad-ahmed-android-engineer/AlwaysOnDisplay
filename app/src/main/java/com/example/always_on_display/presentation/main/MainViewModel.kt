package com.example.always_on_display.presentation.main

import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.always_on_display.domain.usecases.GetClockSettingsUseCase
import com.example.always_on_display.domain.usecases.ToggleServiceUseCase
import com.example.always_on_display.service.OverlayService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    getClockSettingsUseCase: GetClockSettingsUseCase,
    private val toggleServiceUseCase: ToggleServiceUseCase
) : ViewModel() {

    private val _permissionState = MutableStateFlow(false)

    val uiState: StateFlow<MainUiState> = combine(
        getClockSettingsUseCase(),
        _permissionState
    ) { config, hasPermission ->
        MainUiState(
            clockConfig = config,
            hasOverlayPermission = hasPermission,
            isEffectiveServiceActive = config.isServiceActivated && hasPermission
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainUiState()
    )

    fun checkOverlayPermission(context: Context) {
        val granted =
            Settings.canDrawOverlays(context)
        _permissionState.value = granted
    }

    fun toggleService(context: Context, enabled: Boolean, onRequestPermission: () -> Unit) {
        val hasPermission =
            Settings.canDrawOverlays(context)

        if (enabled && !hasPermission) {
            viewModelScope.launch {
                toggleServiceUseCase(false)
            }
            onRequestPermission()
            return
        }

        viewModelScope.launch {
            toggleServiceUseCase(enabled)
            if (enabled && hasPermission) {
                OverlayService.startServiceSafely(context)
            } else {
                OverlayService.stopServiceSafely(context)
            }
        }
    }
}
