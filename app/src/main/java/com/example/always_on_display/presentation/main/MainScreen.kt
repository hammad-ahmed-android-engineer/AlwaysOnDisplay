package com.example.always_on_display.presentation.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.always_on_display.R
import com.example.always_on_display.core.designsystem.components.AppCard
import com.example.always_on_display.core.designsystem.components.AppScaffold
import com.example.always_on_display.core.designsystem.components.AppSwitch
import com.example.always_on_display.core.designsystem.components.AppTopAppBar
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography
import com.example.always_on_display.service.OverlayService

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onNavigateToDigital: () -> Unit,
    onNavigateToAbstract: () -> Unit,
    onNavigateToNeon: () -> Unit,
    onNavigateToAtomic: () -> Unit,
    onNavigateToDisplay: () -> Unit,
    onRequestOverlayPermission: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.checkOverlayPermission(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(uiState.isEffectiveServiceActive) {
        if (uiState.isEffectiveServiceActive) {
            OverlayService.startServiceSafely(context)
        } else {
            OverlayService.stopServiceSafely(context)
        }
    }

    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.app_name)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(AppDimensions.spacing.default)
        ) {
            // Service Toggle Card
            AppCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(AppDimensions.spacing.medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        AppTypography.TitleMedium(text = stringResource(id = R.string.always_on_display_service))
                        AppTypography.SubText(
                            text = if (uiState.isEffectiveServiceActive) {
                                stringResource(id = R.string.service_is_active)
                            } else {
                                stringResource(id = R.string.service_is_disabled)
                            }
                        )
                    }
                    AppSwitch(
                        checked = uiState.isEffectiveServiceActive,
                        onCheckedChange = { enabled ->
                            viewModel.toggleService(
                                context = context,
                                enabled = enabled,
                                onRequestPermission = onRequestOverlayPermission
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(AppDimensions.spacing.large))

            AppTypography.TitleLarge(text = stringResource(id = R.string.clock_categories))

            Spacer(modifier = Modifier.height(AppDimensions.spacing.medium))

            val columns = if (isLandscape) GridCells.Fixed(4) else GridCells.Fixed(2)

            LazyVerticalGrid(
                columns = columns,
                verticalArrangement = Arrangement.spacedBy(AppDimensions.spacing.medium),
                horizontalArrangement = Arrangement.spacedBy(AppDimensions.spacing.medium),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    CategoryCard(
                        title = stringResource(id = R.string.digital_clocks),
                        iconRes = R.drawable.ic_digital_top,
                        onClick = onNavigateToDigital
                    )
                }
                item {
                    CategoryCard(
                        title = stringResource(id = R.string.abstract_clocks),
                        iconRes = R.drawable.ic_abstract_top,
                        onClick = onNavigateToAbstract
                    )
                }
                item {
                    CategoryCard(
                        title = stringResource(id = R.string.neon_clocks),
                        iconRes = R.drawable.ic_neon_top,
                        onClick = onNavigateToNeon
                    )
                }
                item {
                    CategoryCard(
                        title = stringResource(id = R.string.atomic_clocks),
                        iconRes = R.drawable.ic_atomic_top,
                        onClick = onNavigateToAtomic
                    )
                }
            }
        }
    }
}
