package com.example.always_on_display.presentation.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.always_on_display.R
import com.example.always_on_display.core.designsystem.components.AppAnalogClock
import com.example.always_on_display.core.designsystem.components.AppDigitalClock
import com.example.always_on_display.core.designsystem.theme.color.Black
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography
import com.example.always_on_display.core.utils.clickableDebounced
import com.example.always_on_display.domain.model.ClockCategory
import com.example.always_on_display.presentation.abstractclock.AbstractClockResourceMapper
import com.example.always_on_display.presentation.atomic.AtomicClockResourceMapper
import com.example.always_on_display.presentation.neon.NeonClockResourceMapper

@Composable
fun AlwaysOnDisplayScreen(
    viewModel: AlwaysOnDisplayViewModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    overrideCategory: String? = null,
    overrideStyleKey: String? = null
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val config = uiState.clockConfig

    val category = if (!overrideCategory.isNullOrBlank()) {
        try {
            ClockCategory.valueOf(overrideCategory)
        } catch (_: Exception) {
            config.activeCategory
        }
    } else {
        config.activeCategory
    }

    val styleKey = if (!overrideStyleKey.isNullOrBlank()) {
        overrideStyleKey
    } else {
        when (category) {
            ClockCategory.DIGITAL -> config.selectedDigitalStyle
            ClockCategory.ABSTRACT -> config.selectedAbstractStyle
            ClockCategory.NEON -> config.selectedNeonStyle
            ClockCategory.ATOMIC -> config.selectedAtomicStyle
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .clickableDebounced { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(AppDimensions.spacing.large)
        ) {
            when (category) {
                ClockCategory.DIGITAL -> {
                    AppDigitalClock(
                        style = styleKey,
                        hour = uiState.hourStr,
                        minute = uiState.minuteStr,
                        second = uiState.secondStr,
                        dateText = uiState.dateStr,
                        batteryText = uiState.batteryText
                    )
                }
                ClockCategory.ABSTRACT -> {
                    AppAnalogClock(
                        hour = uiState.hourInt,
                        minute = uiState.minuteInt,
                        second = uiState.secondInt,
                        dialResId = AbstractClockResourceMapper.getDialRes(styleKey),
                        hourHandResId = AbstractClockResourceMapper.getHourRes(styleKey),
                        minuteHandResId = AbstractClockResourceMapper.getMinuteRes(styleKey),
                        secondHandResId = AbstractClockResourceMapper.getSecondRes(styleKey),
                        modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer * 5)
                    )
                    Spacer(modifier = Modifier.height(AppDimensions.spacing.large))
                    AppTypography.BodyMedium(text = uiState.batteryText, color = Color.White)
                }
                ClockCategory.NEON -> {
                    AppAnalogClock(
                        hour = uiState.hourInt,
                        minute = uiState.minuteInt,
                        second = uiState.secondInt,
                        dialResId = NeonClockResourceMapper.getDialRes(styleKey),
                        hourHandResId = R.drawable.hour1,
                        minuteHandResId = R.drawable.minute1,
                        secondHandResId = R.drawable.second1,
                        modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer * 5)
                    )
                    Spacer(modifier = Modifier.height(AppDimensions.spacing.large))
                    AppTypography.BodyMedium(text = uiState.batteryText, color = Color.White)
                }
                ClockCategory.ATOMIC -> {
                    AppAnalogClock(
                        hour = uiState.hourInt,
                        minute = uiState.minuteInt,
                        second = uiState.secondInt,
                        dialResId = AtomicClockResourceMapper.getDialRes(styleKey),
                        hourHandResId = R.drawable.hour1,
                        minuteHandResId = R.drawable.minute1,
                        secondHandResId = R.drawable.second1,
                        modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer * 5)
                    )
                    Spacer(modifier = Modifier.height(AppDimensions.spacing.large))
                    AppTypography.BodyMedium(text = uiState.batteryText, color = Color.White)
                }
            }
        }
    }
}
