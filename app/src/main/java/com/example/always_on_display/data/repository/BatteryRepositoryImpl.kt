package com.example.always_on_display.data.repository

import android.content.Context
import com.example.always_on_display.core.utils.batteryStatusFlow
import com.example.always_on_display.domain.model.BatteryInfo
import com.example.always_on_display.domain.repository.BatteryRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [BatteryRepository] using Context batteryStatusFlow extension.
 */
class BatteryRepositoryImpl(
    private val context: Context
) : BatteryRepository {

    override fun getBatteryInfo(): Flow<BatteryInfo> {
        return context.batteryStatusFlow()
    }
}
