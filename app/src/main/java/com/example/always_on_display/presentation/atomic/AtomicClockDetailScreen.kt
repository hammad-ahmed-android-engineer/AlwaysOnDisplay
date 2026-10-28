package com.example.always_on_display.presentation.atomic

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.always_on_display.R
import com.example.always_on_display.core.designsystem.components.AppAnalogClock
import com.example.always_on_display.core.designsystem.components.AppBackVector
import com.example.always_on_display.core.designsystem.components.AppButton
import com.example.always_on_display.core.designsystem.components.AppCard
import com.example.always_on_display.core.designsystem.components.AppIcon
import com.example.always_on_display.core.designsystem.components.AppIconButton
import com.example.always_on_display.core.designsystem.components.AppOutlinedButton
import com.example.always_on_display.core.designsystem.components.AppScaffold
import com.example.always_on_display.core.designsystem.components.AppTopAppBar
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography

@Composable
fun AtomicClockDetailScreen(
    styleKey: String,
    viewModel: AtomicClockViewModel,
    onBack: () -> Unit,
    onApplied: () -> Unit,
    onNavigateToDisplay: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.atomic_clock_title, styleKey),
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
        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(AppDimensions.spacing.default),
                horizontalArrangement = Arrangement.spacedBy(AppDimensions.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AppAnalogClock(
                            hour = uiState.hour,
                            minute = uiState.minute,
                            second = uiState.second,
                            dialResId = AtomicClockResourceMapper.getDialRes(styleKey),
                            hourHandResId = R.drawable.hour1,
                            minuteHandResId = R.drawable.minute1,
                            secondHandResId = R.drawable.second1,
                            modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer * 2.4f)
                        )
                        Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                        AppTypography.BodyMedium(text = uiState.batteryText)
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppCard(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(AppDimensions.spacing.medium),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppTypography.TitleMedium(text = stringResource(id = R.string.clock_settings))
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppOutlinedButton(
                                text = stringResource(id = R.string.preview_fullscreen),
                                onClick = onNavigateToDisplay,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(AppDimensions.spacing.medium))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppDimensions.spacing.medium)
                    ) {
                        AppOutlinedButton(
                            text = stringResource(id = R.string.cancel),
                            onClick = onBack,
                            modifier = Modifier.weight(1f)
                        )
                        AppButton(
                            text = stringResource(id = R.string.apply_clock),
                            onClick = {
                                viewModel.applyAtomicClock(styleKey, onApplied)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(AppDimensions.spacing.default),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AppAnalogClock(
                            hour = uiState.hour,
                            minute = uiState.minute,
                            second = uiState.second,
                            dialResId = AtomicClockResourceMapper.getDialRes(styleKey),
                            hourHandResId = R.drawable.hour1,
                            minuteHandResId = R.drawable.minute1,
                            secondHandResId = R.drawable.second1,
                            modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer * 4)
                        )
                        Spacer(modifier = Modifier.height(AppDimensions.spacing.medium))
                        AppTypography.BodyMedium(text = uiState.batteryText)
                    }
                }

                AppCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(AppDimensions.spacing.medium),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AppTypography.TitleMedium(text = stringResource(id = R.string.clock_settings))
                        Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                        AppOutlinedButton(
                            text = stringResource(id = R.string.preview_fullscreen),
                            onClick = onNavigateToDisplay,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(AppDimensions.spacing.default))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(AppDimensions.spacing.medium)
                ) {
                    AppOutlinedButton(
                        text = stringResource(id = R.string.cancel),
                        onClick = onBack,
                        modifier = Modifier.weight(1f)
                    )
                    AppButton(
                        text = stringResource(id = R.string.apply_clock),
                        onClick = {
                            viewModel.applyAtomicClock(styleKey, onApplied)
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}
