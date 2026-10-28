package com.example.always_on_display.presentation.abstractclock

import com.example.always_on_display.R

/**
 * Utility mapper for resolving Abstract clock drawable resources.
 */
object AbstractClockResourceMapper {

    fun getDialRes(styleKey: String): Int = when (styleKey) {
        "abstract1" -> R.drawable.abstract_dail_1
        "abstract2" -> R.drawable.abstract_dail_2
        "abstract3" -> R.drawable.abstract_dail_3
        "abstract4" -> R.drawable.abstract_dail_4
        "abstract5" -> R.drawable.abstract_dail_5
        "abstract6" -> R.drawable.abstract_dail_6
        "abstract7" -> R.drawable.abstract_dail_7
        "abstract8" -> R.drawable.abstract_dail_8
        else -> R.drawable.abstract_dail_1
    }

    fun getHourRes(styleKey: String): Int = when (styleKey) {
        "abstract1" -> R.drawable.hour1
        "abstract2" -> R.drawable.hour2
        "abstract3" -> R.drawable.hour3
        "abstract4" -> R.drawable.hour4
        "abstract5" -> R.drawable.hour5
        "abstract6" -> R.drawable.hour6
        "abstract7" -> R.drawable.hour7
        "abstract8" -> R.drawable.hour8
        "abstract9" -> R.drawable.hour9
        else -> R.drawable.hour10
    }

    fun getMinuteRes(styleKey: String): Int = when (styleKey) {
        "abstract1" -> R.drawable.minute1
        "abstract2" -> R.drawable.minute2
        "abstract3" -> R.drawable.minute3
        "abstract4" -> R.drawable.minute4
        "abstract5" -> R.drawable.minute5
        "abstract6" -> R.drawable.minute6
        "abstract7" -> R.drawable.minute7
        "abstract8" -> R.drawable.minute8
        "abstract9" -> R.drawable.minute9
        else -> R.drawable.minute10
    }

    fun getSecondRes(styleKey: String): Int = when (styleKey) {
        "abstract1" -> R.drawable.second1
        "abstract2" -> R.drawable.second2
        "abstract3" -> R.drawable.second3
        "abstract4" -> R.drawable.second4
        "abstract5" -> R.drawable.second5
        "abstract6" -> R.drawable.second6
        "abstract7" -> R.drawable.second7
        "abstract8" -> R.drawable.second8
        "abstract9" -> R.drawable.second9
        else -> R.drawable.second10
    }
}
