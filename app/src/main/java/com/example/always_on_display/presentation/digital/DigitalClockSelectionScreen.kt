package com.example.always_on_display.presentation.digital

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.always_on_display.R
import com.example.always_on_display.core.designsystem.components.AppBackVector
import com.example.always_on_display.core.designsystem.components.AppDigitalClock
import com.example.always_on_display.core.designsystem.components.AppIcon
import com.example.always_on_display.core.designsystem.components.AppIconButton
import com.example.always_on_display.core.designsystem.components.AppScaffold
import com.example.always_on_display.core.designsystem.components.AppTopAppBar
import com.example.always_on_display.core.designsystem.components.ClockItemCard
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions

@Composable
fun DigitalClockSelectionScreen(
    viewModel: DigitalClockViewModel,
    onSelectStyle: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columns = if (isLandscape) GridCells.Fixed(3) else GridCells.Fixed(2)

    val styles = listOf(
        "digital1", "digital2", "digital3", "digital4", "digital5",
        "digital6", "digital7", "digital8", "digital9", "digital10"
    )

    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.select_digital_clock),
                navigationIcon = {
                    AppIconButton(onClick = onBack) {
                        AppIcon(
                            imageVector = AppBackVector,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = columns,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(AppDimensions.spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(AppDimensions.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(AppDimensions.spacing.medium)
        ) {
            items(styles.size) { index ->
                val styleKey = styles[index]
                val isSelected = styleKey == uiState.selectedStyle
                ClockItemCard(
                    title = "Digital ${styleKey.removePrefix("digital")}",
                    isSelected = isSelected,
                    onClick = { onSelectStyle(styleKey) }
                ) {
                    AppDigitalClock(
                        style = styleKey,
                        hour = uiState.hour,
                        minute = uiState.minute,
                        second = uiState.second,
                        dateText = uiState.dateText,
                        batteryText = uiState.batteryText,
                        modifier = Modifier.fillMaxSize(),
                        showCardBackground = false
                    )
                }
            }
        }
    }
}
