package com.example.always_on_display.core.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.example.always_on_display.domain.model.BatteryInfo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * Extension function on [Context] to listen for battery state changes as a Flow.
 */
fun Context.batteryStatusFlow(): Flow<BatteryInfo> = callbackFlow {
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
            val status = when (intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)) {
                BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
                BatteryManager.BATTERY_STATUS_DISCHARGING -> "Not Charging"
                BatteryManager.BATTERY_STATUS_FULL -> "Battery Full"
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not Charging"
                else -> "Unknown"
            }
            trySend(BatteryInfo(percentage = level, statusText = status))
        }
    }

    val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    registerReceiver(receiver, filter)

    awaitClose {
        try {
            unregisterReceiver(receiver)
        } catch (_: Exception) {
        }
    }
}
