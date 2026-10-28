package com.example.always_on_display.presentation.abstractclock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.domain.usecases.GetBatteryInfoUseCase
import com.example.always_on_display.domain.usecases.GetClockSettingsUseCase
import com.example.always_on_display.domain.usecases.SaveClockSettingsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Calendar

class AbstractClockViewModel(
    getClockSettingsUseCase: GetClockSettingsUseCase,
    private val saveClockSettingsUseCase: SaveClockSettingsUseCase,
    getBatteryInfoUseCase: GetBatteryInfoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AbstractClockUiState())
    val uiState: StateFlow<AbstractClockUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getClockSettingsUseCase().collect { config ->
                _uiState.value = _uiState.value.copy(
                    selectedStyle = config.selectedAbstractStyle
                )
            }
        }

        viewModelScope.launch {
            getBatteryInfoUseCase().collect { batteryInfo ->
                _uiState.value = _uiState.value.copy(
                    batteryText = "${batteryInfo.statusText} ${batteryInfo.percentage} %"
                )
            }
        }

        viewModelScope.launch {
            while (isActive) {
                val cal = Calendar.getInstance()
                val hour = cal.get(Calendar.HOUR)
                val minute = cal.get(Calendar.MINUTE)
                val second = cal.get(Calendar.SECOND)

                _uiState.value = _uiState.value.copy(
                    hour = hour,
                    minute = minute,
                    second = second
                )
                delay(1000)
            }
        }
    }

    fun applyAbstractClock(styleKey: String, onApplied: () -> Unit) {
        viewModelScope.launch {
            saveClockSettingsUseCase.selectClockStyle(ClockCategory.ABSTRACT, styleKey)
            onApplied()
        }
    }
}
