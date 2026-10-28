package com.example.always_on_display.core.designsystem.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography
import com.example.always_on_display.core.utils.rememberDebouncedClick

/**
 * Common App Button component with 500ms click debouncing.
 */
@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    val debouncedOnClick = rememberDebouncedClick(onClick = onClick)

    Button(
        onClick = debouncedOnClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(AppDimensions.corners.pill),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        AppTypography.ButtonText(
            text = text,
            color = contentColor
        )
    }
}
