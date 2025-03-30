package com.example.semicircle

import android.content.res.Resources
import android.util.TypedValue

class MyUtils {

    companion object {
        fun dpToPx(dp: Float, resources: Resources): Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                resources.displayMetrics
            )
        }
    }
}