package com.example.always_on_display.core.designsystem.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.always_on_display.core.utils.rememberDebouncedClick

/**
 * Common App Radio Button component with 500ms click debouncing.
 */
@Composable
fun AppRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val debouncedOnClick = onClick?.let { rememberDebouncedClick(onClick = it) }

    RadioButton(
        selected = selected,
        onClick = debouncedOnClick,
        modifier = modifier,
        enabled = enabled,
        colors = RadioButtonDefaults.colors(
            selectedColor = MaterialTheme.colorScheme.primary,
            unselectedColor = MaterialTheme.colorScheme.outline
        )
    )
}
