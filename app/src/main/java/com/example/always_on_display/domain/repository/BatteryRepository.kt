package com.example.always_on_display.domain.repository

import com.example.always_on_display.domain.model.BatteryInfo
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for monitoring battery information.
 */
interface BatteryRepository {
    fun getBatteryInfo(): Flow<BatteryInfo>
}
