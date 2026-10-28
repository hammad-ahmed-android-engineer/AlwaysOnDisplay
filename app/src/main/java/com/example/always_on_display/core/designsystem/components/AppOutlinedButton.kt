package com.example.always_on_display.core.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography
import com.example.always_on_display.core.utils.rememberDebouncedClick

/**
 * Common App Outlined Button component with 500ms click debouncing.
 */
@Composable
fun AppOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentColor: Color = MaterialTheme.colorScheme.primary,
) {
    val debouncedOnClick = rememberDebouncedClick(onClick = onClick)

    OutlinedButton(
        onClick = debouncedOnClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(AppDimensions.corners.pill),
        border = BorderStroke(AppDimensions.borders.thin, contentColor)
    ) {
        AppTypography.ButtonText(
            text = text,
            color = contentColor
        )
    }
}
