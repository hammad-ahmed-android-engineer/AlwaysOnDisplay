package com.example.always_on_display

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.always_on_display.core.designsystem.theme.theme.AppTheme
import com.example.always_on_display.presentation.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appContainer = (application as App).appContainer

        setContent {
            AppTheme {
                AppNavigation(
                    appContainer = appContainer,
                    startDestination = "splash"
                )
            }
        }
    }
}
