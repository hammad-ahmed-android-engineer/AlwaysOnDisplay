package com.example.always_on_display.presentation.main

import com.example.always_on_display.domain.model.ClockConfig

data class MainUiState(
    val clockConfig: ClockConfig = ClockConfig(),
    val hasOverlayPermission: Boolean = false,
    val isEffectiveServiceActive: Boolean = false
)
