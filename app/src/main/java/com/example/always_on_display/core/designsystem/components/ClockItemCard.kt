package com.example.always_on_display.core.designsystem.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography
import com.example.always_on_display.core.utils.clickableDebounced

/**
 * Enhanced Clock Item Card for displaying clock options in selection grids/lists.
 */
@Composable
fun ClockItemCard(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    isAnalog: Boolean = false,
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val cardHeight = if (isLandscape) {
        if (isAnalog) {
            AppDimensions.components.emptyStateIconContainer * 2.1f
        } else {
            AppDimensions.components.emptyStateIconContainer * 1.8f
        }
    } else {
        AppDimensions.components.emptyStateIconContainer * 3f
    }

    val borderColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
    }

    val borderWidth = if (isSelected) {
        AppDimensions.borders.medium
    } else {
        AppDimensions.borders.thin
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(cardHeight)
            .clickableDebounced(onClick = onClick),
        shape = RoundedCornerShape(AppDimensions.corners.large),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(borderWidth, borderColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) AppDimensions.elevations.medium else AppDimensions.elevations.low
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(if (isLandscape) AppDimensions.spacing.micro else AppDimensions.spacing.small),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    content()
                }

                Spacer(modifier = Modifier.height(AppDimensions.spacing.micro))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(AppDimensions.corners.small)
                        )
                        .padding(
                            horizontal = AppDimensions.spacing.small,
                            vertical = AppDimensions.spacing.micro
                        ),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppTypography.LabelMedium(
                        text = title,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Selection Badge Indicator
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(AppDimensions.spacing.small)
                        .size(AppDimensions.icons.large)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    AppTypography.BadgeText(
                        text = "✓",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
