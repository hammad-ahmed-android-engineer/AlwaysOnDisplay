package com.example.always_on_display.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.always_on_display.R
import com.example.always_on_display.core.designsystem.components.AppCircularLoader
import com.example.always_on_display.core.designsystem.components.AppScaffold
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavigateNext: (isPermissionGranted: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.checkStatusAndPrepare(context)
    }

    LaunchedEffect(uiState.isReady) {
        if (uiState.isReady) {
            onNavigateNext(uiState.isPermissionGranted)
        }
    }

    AppScaffold(modifier = modifier) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_aod_logo),
                    contentDescription = null,
                    modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer * 2)
                )

                Spacer(modifier = Modifier.height(AppDimensions.spacing.large))

                AppTypography.DisplayMedium(
                    text = stringResource(id = R.string.app_name)
                )

                Spacer(modifier = Modifier.height(AppDimensions.spacing.extraLarge))

                AnimatedVisibility(
                    visible = !uiState.isReady,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    AppCircularLoader()
                }
            }
        }
    }
}
