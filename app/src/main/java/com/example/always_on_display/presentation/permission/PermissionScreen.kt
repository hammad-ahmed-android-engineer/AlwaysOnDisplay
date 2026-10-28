package com.example.always_on_display.presentation.permission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.always_on_display.R
import com.example.always_on_display.core.designsystem.components.AppButton
import com.example.always_on_display.core.designsystem.components.AppCard
import com.example.always_on_display.core.designsystem.components.AppOutlinedButton
import com.example.always_on_display.core.designsystem.components.AppScaffold
import com.example.always_on_display.core.designsystem.components.AppTopAppBar
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography

@Composable
fun PermissionScreen(
    viewModel: PermissionViewModel,
    onNavigateToMain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { _ ->
        requestOverlayPermission(context)
    }

    val handleGrantPermissions = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            requestOverlayPermission(context)
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                viewModel.checkPermission(context)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(uiState.isPermissionGranted) {
        if (uiState.isPermissionGranted) {
            onNavigateToMain()
        }
    }

    AppScaffold(
        modifier = modifier,
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.overlay_permission_title)
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
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_aod_logo),
                        contentDescription = null,
                        modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer + AppDimensions.icons.huge)
                    )
                    Spacer(modifier = Modifier.height(AppDimensions.spacing.medium))
                    AppTypography.HeadlineMedium(
                        text = stringResource(id = R.string.overlay_permission_subtitle)
                    )
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
                                .padding(AppDimensions.spacing.default)
                        ) {
                            AppTypography.BodyMedium(
                                text = stringResource(id = R.string.overlay_permission_description)
                            )
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.BodySmall(
                                text = stringResource(id = R.string.overlay_permission_step1)
                            )
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.micro))
                            AppTypography.BodySmall(
                                text = stringResource(id = R.string.overlay_permission_step2)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(AppDimensions.spacing.medium))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppDimensions.spacing.medium)
                    ) {
                        AppOutlinedButton(
                            text = stringResource(id = R.string.continue_to_app),
                            onClick = onNavigateToMain,
                            modifier = Modifier.weight(1f)
                        )
                        AppButton(
                            text = stringResource(id = R.string.grant_permission),
                            onClick = {
                                handleGrantPermissions()
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
                    .padding(AppDimensions.spacing.default)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(AppDimensions.spacing.medium))

                    Image(
                        painter = painterResource(id = R.drawable.ic_aod_logo),
                        contentDescription = null,
                        modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer + AppDimensions.icons.huge)
                    )

                    Spacer(modifier = Modifier.height(AppDimensions.spacing.large))

                    AppTypography.HeadlineMedium(
                        text = stringResource(id = R.string.overlay_permission_subtitle)
                    )

                    Spacer(modifier = Modifier.height(AppDimensions.spacing.medium))

                    AppCard(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(AppDimensions.spacing.default)
                        ) {
                            AppTypography.BodyMedium(
                                text = stringResource(id = R.string.overlay_permission_description)
                            )
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.medium))
                            AppTypography.BodySmall(
                                text = stringResource(id = R.string.overlay_permission_step1)
                            )
                            Spacer(modifier = Modifier.height(AppDimensions.spacing.small))
                            AppTypography.BodySmall(
                                text = stringResource(id = R.string.overlay_permission_step2)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(AppDimensions.spacing.large))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(AppDimensions.spacing.medium)
                ) {
                    AppOutlinedButton(
                        text = stringResource(id = R.string.continue_to_app),
                        onClick = onNavigateToMain,
                        modifier = Modifier.weight(1f)
                    )
                    AppButton(
                        text = stringResource(id = R.string.grant_permission),
                        onClick = {
                            handleGrantPermissions()
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

private fun requestOverlayPermission(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:${context.packageName}")
        ).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}
