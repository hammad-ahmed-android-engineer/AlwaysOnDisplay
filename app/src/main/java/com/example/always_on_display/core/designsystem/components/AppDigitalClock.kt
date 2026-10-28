package com.example.always_on_display.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.always_on_display.core.designsystem.theme.color.Digital3NeonPurple
import com.example.always_on_display.core.designsystem.theme.color.NeonBlue
import com.example.always_on_display.core.designsystem.theme.color.NeonGreen
import com.example.always_on_display.core.designsystem.theme.color.NeonPink
import com.example.always_on_display.core.designsystem.theme.color.NeonRed
import com.example.always_on_display.core.designsystem.theme.color.NeonYellow
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography

/**
 * Custom Digital Clock Layout renderer for styles digital1 through digital10.
 */
@Composable
fun AppDigitalClock(
    style: String,
    hour: String,
    minute: String,
    second: String,
    dateText: String,
    batteryText: String,
    modifier: Modifier = Modifier,
    showCardBackground: Boolean = false,
    isCompact: Boolean = false
) {
    val secVal = second.toFloatOrNull() ?: 0f

    val clockContent = @Composable {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(if (isCompact) AppDimensions.spacing.micro else AppDimensions.spacing.small),
            contentAlignment = Alignment.Center
        ) {
            when (style) {
                "digital1" -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (isCompact) {
                            AppTypography.TitleLarge(
                                text = "$hour:$minute",
                                color = NeonRed,
                                fontWeight = FontWeight.Bold
                            )
                            AppTypography.LabelSmall(
                                text = second,
                                color = NeonYellow
                            )
                        } else {
                            AppTypography.DisplayLarge(
                                text = "$hour:$minute",
                                color = NeonRed,
                                fontWeight = FontWeight.Bold
                            )
                            AppTypography.LabelLarge(
                                text = second,
                                color = NeonYellow
                            )
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.micro))
                            AppTypography.BodySmall(text = dateText, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.Caption(text = batteryText)
                        }
                    }
                }
                "digital2" -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (isCompact) {
                            AppTypography.TitleLarge(
                                text = "$hour:$minute",
                                color = NeonYellow,
                                fontWeight = FontWeight.ExtraBold
                            )
                        } else {
                            AppTypography.DisplayLarge(
                                text = "$hour:$minute",
                                color = NeonYellow,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.TitleSmall(text = batteryText, color = NeonGreen)
                        }
                    }
                }
                "digital3" -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(contentAlignment = Alignment.Center) {
                            AppCircularProgress(
                                progress = (secVal / 60f) * 100f,
                                modifier = Modifier.size(if (isCompact) AppDimensions.icons.huge * 1.5f else AppDimensions.components.emptyStateIconContainer * 2),
                                color = Digital3NeonPurple,
                                strokeWidth = if (isCompact) AppDimensions.borders.medium else AppDimensions.borders.thick * 2
                            )
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                if (isCompact) {
                                    AppTypography.TitleSmall(
                                        text = "$hour:$minute",
                                        color = Digital3NeonPurple
                                    )
                                } else {
                                    AppTypography.HeadingMedium(
                                        text = "$hour:$minute",
                                        color = Digital3NeonPurple
                                    )
                                    AppTypography.LabelSmall(text = second, color = Color.White)
                                }
                            }
                        }
                        if (!isCompact) {
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.Caption(text = batteryText)
                        }
                    }
                }
                "digital4" -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .background(NeonGreen.copy(alpha = 0.15f), RoundedCornerShape(AppDimensions.corners.medium))
                                .padding(if (isCompact) AppDimensions.spacing.micro else AppDimensions.spacing.small)
                        ) {
                            if (isCompact) {
                                AppTypography.TitleMedium(
                                    text = "$hour:$minute:$second",
                                    color = NeonGreen
                                )
                            } else {
                                AppTypography.DisplayMedium(
                                    text = "$hour:$minute:$second",
                                    color = NeonGreen
                                )
                            }
                        }
                        if (!isCompact) {
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.Caption(text = batteryText)
                        }
                    }
                }
                "digital5" -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (isCompact) {
                            AppTypography.TitleLarge(text = "$hour:$minute", color = NeonBlue)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.micro))
                            AppLinearProgress(
                                progress = secVal / 60f,
                                color = NeonBlue,
                                modifier = Modifier.padding(horizontal = AppDimensions.spacing.small)
                            )
                        } else {
                            AppTypography.DisplayLarge(text = "$hour:$minute", color = NeonBlue)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.micro))
                            AppLinearProgress(
                                progress = secVal / 60f,
                                color = NeonBlue,
                                modifier = Modifier.padding(horizontal = AppDimensions.spacing.medium)
                            )
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.Caption(text = batteryText)
                        }
                    }
                }
                "digital6" -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isCompact) {
                                AppTypography.TitleLarge(text = hour, color = NeonYellow)
                                AppTypography.TitleLarge(text = ":", color = Color.White)
                                AppTypography.TitleLarge(text = minute, color = NeonYellow)
                            } else {
                                AppTypography.DisplayLarge(text = hour, color = NeonYellow)
                                AppTypography.DisplayLarge(text = ":", color = Color.White)
                                AppTypography.DisplayLarge(text = minute, color = NeonYellow)
                            }
                        }
                        if (!isCompact) {
                            AppTypography.TitleMedium(text = second, color = NeonRed)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.Caption(text = "$dateText • $batteryText")
                        }
                    }
                }
                "digital7" -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (isCompact) {
                            AppTypography.TitleLarge(text = "$hour:$minute", color = NeonGreen)
                        } else {
                            AppTypography.DisplayLarge(text = "$hour:$minute", color = NeonGreen)
                            AppTypography.LabelLarge(text = "$second s", color = NeonYellow)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.micro))
                            AppTypography.Caption(text = dateText)
                        }
                    }
                }
                "digital8" -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (isCompact) {
                            AppTypography.TitleLarge(text = "$hour:$minute", color = MaterialTheme.colorScheme.primary)
                        } else {
                            AppTypography.DisplayLarge(text = "$hour:$minute", color = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.BodySmall(text = batteryText)
                        }
                    }
                }
                "digital9" -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (isCompact) {
                            AppTypography.TitleLarge(text = "$hour:$minute", color = Color.White, fontWeight = FontWeight.Bold)
                        } else {
                            AppTypography.DisplayLarge(text = "$hour:$minute", color = Color.White, fontWeight = FontWeight.Bold)
                            AppTypography.TitleSmall(text = second, color = NeonPink)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.micro))
                            AppTypography.Caption(text = dateText)
                        }
                    }
                }
                else -> { // digital10
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (isCompact) {
                                AppTypography.TitleLarge(text = hour, color = NeonPink)
                                AppTypography.TitleLarge(text = ":", color = Color.White)
                                AppTypography.TitleLarge(text = minute, color = NeonBlue)
                            } else {
                                AppTypography.DisplayLarge(text = hour, color = NeonPink)
                                AppTypography.DisplayLarge(text = ":", color = Color.White)
                                AppTypography.DisplayLarge(text = minute, color = NeonBlue)
                            }
                        }
                        if (!isCompact) {
                            AppTypography.TitleSmall(text = second, color = NeonYellow)
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.Caption(text = batteryText)
                        }
                    }
                }
            }
        }
    }

    if (showCardBackground) {
        AppCard(
            modifier = modifier.padding(AppDimensions.spacing.small),
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            clockContent()
        }
    } else {
        Box(modifier = modifier) {
            clockContent()
        }
    }
}
