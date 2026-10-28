package com.example.always_on_display.presentation.digital

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.domain.usecases.GetClockSettingsUseCase
import com.example.always_on_display.domain.usecases.SaveClockSettingsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DigitalClockViewModel(
    getClockSettingsUseCase: GetClockSettingsUseCase,
    private val saveClockSettingsUseCase: SaveClockSettingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DigitalClockUiState())
    val uiState: StateFlow<DigitalClockUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getClockSettingsUseCase().collect { config ->
                _uiState.value = _uiState.value.copy(
                    selectedStyle = config.selectedDigitalStyle
                )
            }
        }

        viewModelScope.launch {
            while (isActive) {
                val now = Date()
                val hour = SimpleDateFormat("HH", Locale.getDefault()).format(now)
                val minute = SimpleDateFormat("mm", Locale.getDefault()).format(now)
                val second = SimpleDateFormat("ss", Locale.getDefault()).format(now)
                val dateStr = SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(now)

                _uiState.value = _uiState.value.copy(
                    hour = hour,
                    minute = minute,
                    second = second,
                    dateText = dateStr
                )
                delay(1000)
            }
        }
    }

    fun applyDigitalClock(styleKey: String, onApplied: () -> Unit) {
        viewModelScope.launch {
            saveClockSettingsUseCase.selectClockStyle(ClockCategory.DIGITAL, styleKey)
            onApplied()
        }
    }
}
