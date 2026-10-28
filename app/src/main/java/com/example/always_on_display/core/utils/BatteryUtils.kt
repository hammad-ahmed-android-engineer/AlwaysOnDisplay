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
    fun parseBatteryInfo(intent: Intent?): BatteryInfo {
        if (intent == null) return BatteryInfo(percentage = 100, statusText = "Not Charging")

        val rawLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val percentage = if (rawLevel >= 0 && scale > 0) {
            ((rawLevel.toFloat() / scale.toFloat()) * 100f).toInt()
        } else {
            0
        }

        val statusInt = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)

        val isPlugged = plugged == BatteryManager.BATTERY_PLUGGED_AC ||
                plugged == BatteryManager.BATTERY_PLUGGED_USB ||
                plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS

        val isCharging = isPlugged || statusInt == BatteryManager.BATTERY_STATUS_CHARGING

        val statusText = when {
            statusInt == BatteryManager.BATTERY_STATUS_FULL -> "Battery Full"
            isCharging -> "Charging"
            else -> "Not Charging"
        }

        return BatteryInfo(percentage = percentage, statusText = statusText)
    }

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            trySend(parseBatteryInfo(intent))
        }
    }

    val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    val stickyIntent = registerReceiver(receiver, filter)
    stickyIntent?.let { trySend(parseBatteryInfo(it)) }

    awaitClose {
        try {
            unregisterReceiver(receiver)
        } catch (_: Exception) {
        }
    }
}
