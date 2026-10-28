package com.example.always_on_display

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.always_on_display.core.designsystem.theme.theme.AppTheme
import com.example.always_on_display.di.ViewModelFactory
import com.example.always_on_display.presentation.display.AlwaysOnDisplayScreen
import com.example.always_on_display.presentation.display.AlwaysOnDisplayViewModel

/**
 * Dedicated Activity for rendering the full-screen Always On Display animation over the lock screen.
 */
class AlwaysOnDisplayActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        configureLockScreenFlags()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appContainer = (application as App).appContainer
        val factory = ViewModelFactory(appContainer)

        val overrideCategory = intent?.getStringExtra("EXTRA_CATEGORY")
        val overrideStyleKey = intent?.getStringExtra("EXTRA_STYLE_KEY")

        setContent {
            AppTheme {
                BackHandler {
                    dismissAodActivity()
                }

                val vm: AlwaysOnDisplayViewModel = viewModel(factory = factory)
                AlwaysOnDisplayScreen(
                    viewModel = vm,
                    overrideCategory = overrideCategory,
                    overrideStyleKey = overrideStyleKey,
                    onDismiss = { dismissAodActivity() }
                )
            }
        }
    }

    private fun dismissAodActivity() {
        finishAndRemoveTask()
    }

    private fun configureLockScreenFlags() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val keyguardManager = getSystemService(KEYGUARD_SERVICE) as? KeyguardManager
            keyguardManager?.requestDismissKeyguard(this, null)
        }
        @Suppress("DEPRECATION")
        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )
    }

    companion object {
        fun start(context: Context, category: String? = null, styleKey: String? = null) {
            val intent = Intent(context, AlwaysOnDisplayActivity::class.java).apply {
                if (!category.isNullOrBlank()) putExtra("EXTRA_CATEGORY", category)
                if (!styleKey.isNullOrBlank()) putExtra("EXTRA_STYLE_KEY", styleKey)
                addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_SINGLE_TOP
                )
            }
            context.startActivity(intent)
        }
    }
}
