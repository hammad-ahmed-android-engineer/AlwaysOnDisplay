package com.example.always_on_display.presentation.main

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import com.example.always_on_display.core.designsystem.components.AppCard
import com.example.always_on_display.core.designsystem.theme.dimensions.AppDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography

/**
 * Category Card component for displaying individual clock categories.
 */
@Composable
fun CategoryCard(
    title: String,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    AppCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppDimensions.spacing.medium),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    modifier = Modifier.size(AppDimensions.icons.huge)
                )
                Spacer(modifier = Modifier.width(AppDimensions.spacing.small))
                AppTypography.TitleSmall(text = title)
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppDimensions.spacing.large),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = title,
                    modifier = Modifier.size(AppDimensions.components.emptyStateIconContainer)
                )
                Spacer(modifier = Modifier.height(AppDimensions.spacing.medium))
                AppTypography.TitleMedium(text = title)
            }
        }
    }
}
