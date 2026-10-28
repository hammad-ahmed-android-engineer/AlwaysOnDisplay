package com.example.always_on_display.presentation.display

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val category = if (!overrideCategory.isNullOrBlank()) {
        try {
            ClockCategory.valueOf(overrideCategory)
        } catch (_: Exception) {
            config.selectedCategory
        }
    } else {
        config.selectedCategory
    }

    val styleKey = if (!overrideStyleKey.isNullOrBlank()) {
        overrideStyleKey
    } else {
        config.selectedStyle
    }

    val clockSize = if (isLandscape) {
        AppDimensions.components.emptyStateIconContainer * 2.2f
    } else {
        AppDimensions.components.emptyStateIconContainer * 4.5f
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Black)
            .clickableDebounced { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppDimensions.spacing.medium),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
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
                            modifier = Modifier.size(clockSize)
                        )
                        Spacer(modifier = Modifier.width(AppDimensions.spacing.large))
                        Column(horizontalAlignment = Alignment.Start) {
                            AppTypography.TitleMedium(text = uiState.dateStr, color = Color.White)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.BodyMedium(text = uiState.batteryText, color = Color.White)
                        }
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
                            modifier = Modifier.size(clockSize)
                        )
                        Spacer(modifier = Modifier.width(AppDimensions.spacing.large))
                        Column(horizontalAlignment = Alignment.Start) {
                            AppTypography.TitleMedium(text = uiState.dateStr, color = Color.White)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.BodyMedium(text = uiState.batteryText, color = Color.White)
                        }
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
                            modifier = Modifier.size(clockSize)
                        )
                        Spacer(modifier = Modifier.width(AppDimensions.spacing.large))
                        Column(horizontalAlignment = Alignment.Start) {
                            AppTypography.TitleMedium(text = uiState.dateStr, color = Color.White)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.BodyMedium(text = uiState.batteryText, color = Color.White)
                        }
                    }
                }
            }
        } else {
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
                            modifier = Modifier.size(clockSize)
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
                            modifier = Modifier.size(clockSize)
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
                            modifier = Modifier.size(clockSize)
                        )
                        Spacer(modifier = Modifier.height(AppDimensions.spacing.large))
                        AppTypography.BodyMedium(text = uiState.batteryText, color = Color.White)
                    }
                }
            }
        }
    }
}
