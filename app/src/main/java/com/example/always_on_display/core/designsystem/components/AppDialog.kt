package com.example.always_on_display.core.designsystem.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography

/**
 * Common App Dialog component using custom view components for all texts and actions.
 */
@Composable
fun AppDialog(
    onDismissRequest: () -> Unit,
    title: String,
    text: String,
    confirmButtonText: String = "OK",
    onConfirm: () -> Unit,
    dismissButtonText: String? = "Cancel",
    onDismiss: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        title = {
            AppTypography.TitleLarge(text = title)
        },
        text = {
            AppTypography.BodyMedium(text = text)
        },
        confirmButton = {
            AppTextButton(
                text = confirmButtonText,
                onClick = onConfirm
            )
        },
        dismissButton = dismissButtonText?.let { btnText ->
            {
                AppTextButton(
                    text = btnText,
                    onClick = {
                        onDismiss?.invoke()
                        onDismissRequest()
                    }
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
}
