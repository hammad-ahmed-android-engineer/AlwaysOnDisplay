package com.example.always_on_display.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.utils.clickableDebounced

/**
 * Common App Card component with optional 500ms click debouncing.
 */
@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color? = MaterialTheme.colorScheme.outline,
    content: @Composable () -> Unit
) {
    val cardModifier = if (onClick != null) {
        modifier.clickableDebounced(onClick = onClick)
    } else {
        modifier
    }

    Card(
        modifier = cardModifier,
        shape = RoundedCornerShape(AppDimensions.corners.medium),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        border = borderColor?.let { BorderStroke(AppDimensions.borders.thin, it) },
        elevation = CardDefaults.cardElevation(defaultElevation = AppDimensions.elevations.low)
    ) {
        content()
    }
}
