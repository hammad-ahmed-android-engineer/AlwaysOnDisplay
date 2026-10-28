package com.example.always_on_display.core.utils

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role

/**
 * Extension on [Modifier] to prevent double clicks within a [debounceIntervalMillis] timeframe (default 500ms).
 */
fun Modifier.clickableDebounced(
    debounceIntervalMillis: Long = 500L,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier = composed {
    var lastClickTime by remember { mutableLongStateOf(0L) }

    this.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role
    ) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= debounceIntervalMillis) {
            lastClickTime = currentTime
            onClick()
        }
    }
}

/**
 * Composable extension/helper returning a debounced click lambda function.
 */
@Composable
fun rememberDebouncedClick(
    debounceIntervalMillis: Long = 500L,
    onClick: () -> Unit
): () -> Unit {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    return remember(onClick, debounceIntervalMillis) {
        {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime >= debounceIntervalMillis) {
                lastClickTime = currentTime
                onClick()
            }
        }
    }
}
