package com.example.always_on_display.core.designsystem.components

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.always_on_display.core.utils.rememberDebouncedClick

/**
 * Common App Checkbox component with 500ms click debouncing.
 */
@Composable
fun AppCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val debouncedOnCheckedChange = onCheckedChange?.let { cb ->
        rememberDebouncedClick { cb(!checked) }
    }

    Checkbox(
        checked = checked,
        onCheckedChange = { debouncedOnCheckedChange?.invoke() },
        modifier = modifier,
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary,
            checkmarkColor = MaterialTheme.colorScheme.onPrimary,
            uncheckedColor = MaterialTheme.colorScheme.outline
        )
    )
}
