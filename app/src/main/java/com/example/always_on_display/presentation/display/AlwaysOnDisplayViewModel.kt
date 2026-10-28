package com.example.always_on_display.presentation.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.always_on_display.domain.usecases.GetBatteryInfoUseCase
import com.example.always_on_display.domain.usecases.GetClockSettingsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AlwaysOnDisplayViewModel(
    getClockSettingsUseCase: GetClockSettingsUseCase,
    getBatteryInfoUseCase: GetBatteryInfoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlwaysOnDisplayUiState())
    val uiState: StateFlow<AlwaysOnDisplayUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getClockSettingsUseCase().collect { config ->
                _uiState.value = _uiState.value.copy(clockConfig = config)
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
                val now = Date()
                val cal = Calendar.getInstance()
                val hourInt = cal.get(Calendar.HOUR)
                val minuteInt = cal.get(Calendar.MINUTE)
                val secondInt = cal.get(Calendar.SECOND)

                val hourStr = SimpleDateFormat("HH", Locale.getDefault()).format(now)
                val minuteStr = SimpleDateFormat("mm", Locale.getDefault()).format(now)
                val secondStr = SimpleDateFormat("ss", Locale.getDefault()).format(now)
                val dateStr = SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(now)

                _uiState.value = _uiState.value.copy(
                    hourInt = hourInt,
                    minuteInt = minuteInt,
                    secondInt = secondInt,
                    hourStr = hourStr,
                    minuteStr = minuteStr,
                    secondStr = secondStr,
                    dateStr = dateStr
                )
                delay(1000)
            }
        }
    }
}
