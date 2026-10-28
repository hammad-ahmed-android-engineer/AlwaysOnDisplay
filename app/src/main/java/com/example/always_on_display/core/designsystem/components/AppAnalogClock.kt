package com.example.always_on_display.core.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlin.math.cos
import kotlin.math.sin

/**
 * Custom Canvas-based Analog Clock component supporting Abstract, Neon, and Atomic styles natively in Compose.
 */
@Composable
fun AppAnalogClock(
    hour: Int,
    minute: Int,
    second: Int,
    modifier: Modifier = Modifier,
    @DrawableRes dialResId: Int? = null,
    @DrawableRes hourHandResId: Int? = null,
    @DrawableRes minuteHandResId: Int? = null,
    @DrawableRes secondHandResId: Int? = null,
    accentColor: Color = Color.White
) {
    val dialBitmap = dialResId?.let { ImageBitmap.imageResource(id = it) }
    val hourBitmap = hourHandResId?.let { ImageBitmap.imageResource(id = it) }
    val minuteBitmap = minuteHandResId?.let { ImageBitmap.imageResource(id = it) }
    val secondBitmap = secondHandResId?.let { ImageBitmap.imageResource(id = it) }

    Canvas(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize()
    ) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val radius = size.width / 2f

        // 1. Draw Dial Image or Default Dial Circle
        if (dialBitmap != null) {
            drawImage(
                image = dialBitmap,
                dstOffset = IntOffset.Zero,
                dstSize = IntSize(size.width.toInt(), size.height.toInt())
            )
        } else {
            drawCircle(
                color = accentColor.copy(alpha = 0.15f),
                radius = radius,
                center = center
            )
        }

        // Calculate rotation angles
        val secondAngle = (second / 60f) * 360f
        val minuteAngle = ((minute + second / 60f) / 60f) * 360f
        val hourAngle = (((hour % 12) + minute / 60f) / 12f) * 360f

        // 2. Draw Hour Hand
        if (hourBitmap != null) {
            rotate(degrees = hourAngle, pivot = center) {
                drawImage(
                    image = hourBitmap,
                    dstOffset = IntOffset.Zero,
                    dstSize = IntSize(size.width.toInt(), size.height.toInt())
                )
            }
        } else {
            drawHand(center, hourAngle, radius * 0.5f, accentColor, 8f)
        }

        // 3. Draw Minute Hand
        if (minuteBitmap != null) {
            rotate(degrees = minuteAngle, pivot = center) {
                drawImage(
                    image = minuteBitmap,
                    dstOffset = IntOffset.Zero,
                    dstSize = IntSize(size.width.toInt(), size.height.toInt())
                )
            }
        } else {
            drawHand(center, minuteAngle, radius * 0.75f, accentColor, 5f)
        }

        // 4. Draw Second Hand
        if (secondBitmap != null) {
            rotate(degrees = secondAngle, pivot = center) {
                drawImage(
                    image = secondBitmap,
                    dstOffset = IntOffset.Zero,
                    dstSize = IntSize(size.width.toInt(), size.height.toInt())
                )
            }
        } else {
            drawHand(center, secondAngle, radius * 0.85f, Color.Red, 3f)
        }
    }
}

private fun DrawScope.drawHand(
    center: Offset,
    angleDegrees: Float,
    length: Float,
    color: Color,
    strokeWidth: Float
) {
    val rad = Math.toRadians((angleDegrees - 90).toDouble())
    val end = Offset(
        x = (center.x + length * cos(rad)).toFloat(),
        y = (center.y + length * sin(rad)).toFloat()
    )
    drawLine(
        color = color,
        start = center,
        end = end,
        strokeWidth = strokeWidth
    )
}
