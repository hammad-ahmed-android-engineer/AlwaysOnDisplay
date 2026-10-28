package com.example.always_on_display.presentation.neon

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
import com.example.always_on_display.core.designsystem.components.AppAnalogClock
import com.example.always_on_display.core.designsystem.components.AppBackVector
import com.example.always_on_display.core.designsystem.components.AppIcon
import com.example.always_on_display.core.designsystem.components.AppIconButton
import com.example.always_on_display.core.designsystem.components.AppScaffold
import com.example.always_on_display.core.designsystem.components.AppTopAppBar
import com.example.always_on_display.core.designsystem.components.ClockItemCard
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions

@Composable
fun NeonClockSelectionScreen(
    viewModel: NeonClockViewModel,
    onSelectStyle: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columns = if (isLandscape) GridCells.Fixed(3) else GridCells.Fixed(2)

    val styles = listOf(
        "neon1", "neon2", "neon3", "neon4", "neon5",
        "neon6", "neon7", "neon8", "neon9", "neon10"
    )

    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.select_neon_clock),
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
                    title = "Neon ${styleKey.removePrefix("neon")}",
                    isSelected = isSelected,
                    onClick = { onSelectStyle(styleKey) }
                ) {
                    val dialRes = NeonClockResourceMapper.getDialRes(styleKey)

                    AppAnalogClock(
                        hour = uiState.hour,
                        minute = uiState.minute,
                        second = uiState.second,
                        dialResId = dialRes,
                        hourHandResId = R.drawable.hour1,
                        minuteHandResId = R.drawable.minute1,
                        secondHandResId = R.drawable.second1,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
