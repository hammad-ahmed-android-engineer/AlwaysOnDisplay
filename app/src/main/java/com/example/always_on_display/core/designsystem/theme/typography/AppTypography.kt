package com.example.always_on_display.core.designsystem.theme.typography

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.always_on_display.core.designsystem.components.AppTextView
import com.example.always_on_display.core.designsystem.theme.dimensions.LocalDimensions
import com.example.always_on_display.core.designsystem.theme.dimensions.TypeMetric
import com.example.always_on_display.core.designsystem.theme.dimensions.TypeScale

/**
 * Centralized AppTypography tokens and composables for the Application.
 */
object AppTypography {

    // region Theme typography

    private val TrackingDisplayLarge = (-0.5).sp
    private val TrackingDisplayMedium = (-0.25).sp
    private val TrackingHeadlineLarge = (-0.2).sp
    private val TrackingTight = 0.1.sp
    private val TrackingBody = 0.2.sp
    private val TrackingSmall = 0.3.sp
    private val TrackingCaption = 0.5.sp

    @Composable
    @ReadOnlyComposable
    fun toMaterial3Typography(
        fontFamily: FontFamily = FontFamily.SansSerif
    ): Typography = material3Typography(LocalDimensions.current.typeScale, fontFamily)

    fun material3Typography(
        scale: TypeScale,
        fontFamily: FontFamily = FontFamily.SansSerif,
    ): Typography {
        fun style(
            metric: TypeMetric,
            weight: FontWeight,
            letterSpacing: TextUnit = TextUnit.Unspecified,
        ) = TextStyle(
            fontFamily = fontFamily,
            fontWeight = weight,
            fontSize = metric.fontSize,
            lineHeight = metric.lineHeight,
            letterSpacing = letterSpacing,
        )

        return Typography(
            displayLarge = style(scale.displayLarge, FontWeight.ExtraBold, TrackingDisplayLarge),
            displayMedium = style(scale.displayMedium, FontWeight.Bold, TrackingDisplayMedium),
            displaySmall = style(scale.displaySmall, FontWeight.Bold),
            headlineLarge = style(scale.headlineLarge, FontWeight.Bold, TrackingHeadlineLarge),
            headlineMedium = style(scale.headlineMedium, FontWeight.SemiBold),
            headlineSmall = style(scale.headlineSmall, FontWeight.SemiBold),
            titleLarge = style(scale.titleLarge, FontWeight.SemiBold),
            titleMedium = style(scale.titleMedium, FontWeight.Medium, TrackingTight),
            titleSmall = style(scale.titleSmall, FontWeight.Medium, TrackingTight),
            bodyLarge = style(scale.bodyLarge, FontWeight.Normal, TrackingBody),
            bodyMedium = style(scale.bodyMedium, FontWeight.Normal, TrackingBody),
            bodySmall = style(scale.bodySmall, FontWeight.Normal, TrackingSmall),
            labelLarge = style(scale.labelLarge, FontWeight.SemiBold, TrackingTight),
            labelMedium = style(scale.labelMedium, FontWeight.Medium, TrackingSmall),
            labelSmall = style(scale.labelSmall, FontWeight.Medium, TrackingCaption),
        )
    }

    // endregion

    // region Core

    @Composable
    private fun StyledText(
        text: String,
        style: TextStyle,
        modifier: Modifier,
        color: Color,
        fontSize: TextUnit,
        fontWeight: FontWeight?,
        textDecoration: TextDecoration?,
        textAlign: TextAlign?,
        maxLines: Int,
        overflow: TextOverflow,
    ) {
        AppTextView(
            text = text,
            modifier = modifier,
            color = color,
            textAlign = textAlign,
            maxLines = maxLines,
            overflow = overflow,
            style = style.merge(
                fontSize = fontSize,
                fontWeight = fontWeight,
                textDecoration = textDecoration,
            ),
        )
    }

    @Composable
    fun Text(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        fontStyle: FontStyle? = null,
        letterSpacing: TextUnit = TextUnit.Unspecified,
        lineHeight: TextUnit = TextUnit.Unspecified,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
        minLines: Int = 1,
        style: TextStyle = LocalTextStyle.current,
    ) {
        AppTextView(
            text = text,
            modifier = modifier,
            color = color,
            fontSize = fontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
            style = style,
        )
    }

    // endregion

    // region Display & hero

    @Composable
    fun DisplayLarge(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.displayLarge, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun DisplayMedium(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.displayMedium, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun DisplaySmall(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.displaySmall, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    // endregion

    // region Headlines

    @Composable
    fun HeadlineLarge(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.headlineLarge, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun HeadlineMedium(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.headlineMedium, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun HeadlineSmall(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.headlineSmall, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun HeadingLarge(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = HeadlineLarge(text, modifier, color, fontSize, fontWeight, textDecoration, textAlign, maxLines, overflow)

    @Composable
    fun HeadingMedium(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = HeadlineMedium(text, modifier, color, fontSize, fontWeight, textDecoration, textAlign, maxLines, overflow)

    @Composable
    fun HeadingSmall(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = HeadlineSmall(text, modifier, color, fontSize, fontWeight, textDecoration, textAlign, maxLines, overflow)

    // endregion

    // region Titles

    @Composable
    fun TitleLarge(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = FontWeight.Bold,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.titleLarge, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun TitleMedium(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = FontWeight.SemiBold,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.titleMedium, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun TitleSmall(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.titleSmall, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    // endregion

    // region Body

    @Composable
    fun BodyLarge(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.bodyLarge, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun BodyMedium(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.bodyMedium, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun BodySmall(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.bodySmall, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    // endregion

    // region Labels, buttons & badges

    @Composable
    fun LabelLarge(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.labelLarge, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun LabelMedium(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.labelMedium, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun LabelSmall(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.labelSmall, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun ButtonText(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = FontWeight.Bold,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.labelLarge, modifier, color, fontSize,
        fontWeight, null, textAlign, maxLines, overflow,
    )

    @Composable
    fun BadgeText(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Unspecified,
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = FontWeight.Bold,
        textAlign: TextAlign? = null,
        maxLines: Int = 1,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.labelSmall, modifier, color, fontSize,
        fontWeight, null, textAlign, maxLines, overflow,
    )

    // endregion

    // region Subtext, captions & helper text

    private const val SubTextAlpha = 0.75f
    private const val CaptionAlpha = 0.6f
    private const val HelperTextAlpha = 0.7f

    @Composable
    fun SubText(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = SubTextAlpha),
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.labelMedium, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun Caption(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = CaptionAlpha),
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = FontWeight.Normal,
        textDecoration: TextDecoration? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.labelSmall, modifier, color, fontSize,
        fontWeight, textDecoration, textAlign, maxLines, overflow,
    )

    @Composable
    fun HelperText(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = HelperTextAlpha),
        fontSize: TextUnit = TextUnit.Unspecified,
        fontWeight: FontWeight? = null,
        textAlign: TextAlign? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Ellipsis,
    ) = StyledText(
        text, MaterialTheme.typography.bodySmall, modifier, color, fontSize,
        fontWeight, null, textAlign, maxLines, overflow,
    )

    // endregion
}
