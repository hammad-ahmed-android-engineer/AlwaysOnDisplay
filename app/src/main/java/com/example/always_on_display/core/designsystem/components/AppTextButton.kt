package com.example.always_on_display.core.designsystem.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography
import com.example.always_on_display.core.utils.rememberDebouncedClick

/**
 * Common App Text Button component with 500ms click debouncing.
 */
@Composable
fun AppTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentColor: Color = MaterialTheme.colorScheme.primary,
) {
    val debouncedOnClick = rememberDebouncedClick(onClick = onClick)

    TextButton(
        onClick = debouncedOnClick,
        modifier = modifier,
        enabled = enabled
    ) {
        AppTypography.ButtonText(
            text = text,
            color = contentColor
        )
    }
}
