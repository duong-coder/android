package com.example.semicircle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.view.marginBottom
import com.example.centercropimage.R
import kotlin.math.cos
import kotlin.math.cosh
import kotlin.math.sin

class DailyProgressBar : View {

    constructor(context: Context?) : super(context) {
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs, 0) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    private val START_ANGEL = 210f
    private val SWEEP_ANGEL = 120f
    private val progressHeight = MyUtils.dpToPx(16f, resources)
    private val progressHalfOfHeight = MyUtils.dpToPx(8f, resources)
    // 8f is margin, 8f is 1/2 of progressHeight
    private val progressMarginHorizontal = MyUtils.dpToPx(8f + 8f, resources)

    private val progressPaint = Paint().apply {
        color = resources.getColor(R.color.yellow, null)
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = progressHeight
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // cut around circle
        val spaceHorizontal = (1 + cos(START_ANGEL)) * width/2 - progressMarginHorizontal
        val spaceTop = height - (1 - sin(START_ANGEL)) * width/2 - progressHalfOfHeight

        // float left, float top, float right, float bottom
        val rectFWillDraw = RectF(
            0f - (spaceHorizontal),
            0f + (progressHalfOfHeight + spaceTop) - (spaceHorizontal),
            width.toFloat() + (spaceHorizontal),
            width.toFloat() + (progressHalfOfHeight + spaceTop) + (spaceHorizontal)
        )

        // @NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter,
        //            @NonNull Paint paint
        canvas.drawArc(rectFWillDraw, START_ANGEL, SWEEP_ANGEL, false, progressPaint)
    }

}