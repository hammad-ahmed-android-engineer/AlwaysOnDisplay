package com.example.always_on_display.domain.usecases

import com.example.always_on_display.domain.model.BatteryInfo
import com.example.always_on_display.domain.repository.BatteryRepository
import kotlinx.coroutines.flow.Flow

class GetBatteryInfoUseCase(
    private val repository: BatteryRepository
) {
    operator fun invoke(): Flow<BatteryInfo> {
        return repository.getBatteryInfo()
    }
}
