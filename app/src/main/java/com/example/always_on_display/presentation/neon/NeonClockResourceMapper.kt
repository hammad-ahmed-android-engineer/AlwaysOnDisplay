package com.example.always_on_display.presentation.neon

import com.example.always_on_display.R

/**
 * Utility mapper for resolving Neon clock drawable resources.
 */
object NeonClockResourceMapper {

    fun getDialRes(styleKey: String): Int = when (styleKey) {
        "neon1" -> R.drawable.neon_dail_1
        "neon2" -> R.drawable.neon_dail_2
        "neon3" -> R.drawable.neon_dail_3
        "neon4" -> R.drawable.neon_dail_4
        "neon5" -> R.drawable.neon_dail_5
        "neon6" -> R.drawable.neon_dail_6
        "neon7" -> R.drawable.neon_dail_7
        "neon8" -> R.drawable.neon_dail_8
        "neon9" -> R.drawable.neon_daill_9
        else -> R.drawable.neon_dail_10
    }
}
