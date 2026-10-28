package com.example.always_on_display.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ServiceInfo
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.core.app.NotificationCompat
import com.example.always_on_display.AlwaysOnDisplayActivity
import com.example.always_on_display.MainActivity
import com.example.always_on_display.R

class OverlayService : Service() {

    private val channelId = "alwaysOnDisplayChannel"

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        registerOverlayReceiver()
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                val channel = NotificationChannel(
                    channelId,
                    "Always On Display Active Service",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Shows ongoing notification when Always On Display is active"
                }
                val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)

                val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                } else {
                    PendingIntent.FLAG_UPDATE_CURRENT
                }

                val openAppIntent = Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                val pendingIntent = PendingIntent.getActivity(
                    this,
                    0,
                    openAppIntent,
                    pendingIntentFlags
                )

                val notification = NotificationCompat.Builder(this, channelId)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(getString(R.string.aod_active_description))
                    .setSmallIcon(R.drawable.ic_aod_logo)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .build()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    startForeground(
                        1,
                        notification,
                        ServiceInfo.FOREGROUND_SERVICE_TYPE_SPECIAL_USE
                    )
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    startForeground(
                        1,
                        notification,
                        ServiceInfo.FOREGROUND_SERVICE_TYPE_NONE
                    )
                } else {
                    startForeground(1, notification)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        unregisterOverlayReceiver()
        super.onDestroy()
    }

    private fun registerOverlayReceiver() {
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_OFF)
            addAction(Intent.ACTION_USER_PRESENT)
        }
        registerReceiver(overlayReceiver, filter)
    }

    private fun unregisterOverlayReceiver() {
        try {
            unregisterReceiver(overlayReceiver)
        } catch (_: Exception) {
        }
    }

    private val overlayReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == Intent.ACTION_SCREEN_OFF) {
                showOverlayActivity(context)
            }
        }
    }

    private fun showOverlayActivity(context: Context) {
        AlwaysOnDisplayActivity.start(context)
    }

    companion object {
        fun startServiceSafely(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
                return
            }
            try {
                val intent = Intent(context, OverlayService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun stopServiceSafely(context: Context) {
            try {
                val intent = Intent(context, OverlayService::class.java)
                context.stopService(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
