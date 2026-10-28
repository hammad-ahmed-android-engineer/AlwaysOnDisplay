package com.example.always_on_display.presentation.atomic

import com.example.always_on_display.R

/**
 * Utility mapper for resolving Atomic clock drawable resources.
 */
object AtomicClockResourceMapper {

    fun getDialRes(styleKey: String): Int = when (styleKey) {
        "atomic1" -> R.drawable.atomic_dial_1
        "atomic2" -> R.drawable.atomic_dial_2
        "atomic3" -> R.drawable.atomic_dial_3
        "atomic4" -> R.drawable.atomic_dial_4
        "atomic5" -> R.drawable.atomic_dial_5
        "atomic6" -> R.drawable.atomic_dial_6
        "atomic7" -> R.drawable.atomic_dial_7
        "atomic8" -> R.drawable.atomic_dial_8
        "atomic9" -> R.drawable.atomic_dial_9
        else -> R.drawable.atomic_dial_10
    }
}
