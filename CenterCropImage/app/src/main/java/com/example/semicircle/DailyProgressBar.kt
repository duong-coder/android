package com.example.semicircle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
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
    private val START_ANGEL_RADIAN = Math.toRadians(START_ANGEL.toDouble()).toFloat()
    private val SWEEP_ANGEL = 120f

    private val progressHeight = MyUtils.dpToPx(16f, resources)
    private val progressHalfOfHeight = MyUtils.dpToPx(8f, resources)
    private val progressQuarterOfHeight = MyUtils.dpToPx(4f, resources)
    // 8f is margin, 8f is 1/2 of progressHeight
    private val progressMarginHorizontal = MyUtils.dpToPx(8f + 8f, resources)
    private val marginHorizontal = MyUtils.dpToPx(8f, resources)
    private val paddingTop = MyUtils.dpToPx(24f, resources)

    private val progressPaint = Paint().apply {
        color = resources.getColor(R.color.yellow, null)
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = progressHeight
        isAntiAlias = true
    }

    private val progressBackgroundPaint = Paint().apply {
        color = resources.getColor(R.color.grey, null)
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeWidth = progressHeight
        isAntiAlias = true
    }

    //for animation
    private var progressValue = 0f // 0 - 100

    //for show image
    lateinit var onAnimationStart: (topValue: Float, leftValue: Float) -> Unit
    lateinit var onAnimation: (topValue: Float, leftValue: Float) -> Unit
    lateinit var onAnimationEnd: (topValue: Float, leftValue: Float) -> Unit

    fun setProgressValue(value: Float) {
        progressValue = value
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // cut around circle
        val spaceHorizontal = (1 + cos(START_ANGEL_RADIAN)) * width/2 - marginHorizontal
        val spaceHorizontalWidthProgress = spaceHorizontal - progressHalfOfHeight
        val spaceTop = progressHalfOfHeight + paddingTop

        // float left, float top, float right, float bottom
        val rectFWillDraw = RectF(
            0f - spaceHorizontalWidthProgress,
            0f + spaceTop,
            width.toFloat() + spaceHorizontalWidthProgress,
            width.toFloat() + spaceHorizontalWidthProgress*2 + spaceTop
        )

        val centerX = rectFWillDraw.centerX()
        val centerY = rectFWillDraw.centerY()
        Log.d("zzzd", "centerX $centerX centerY $centerY")
        Log.d("zzzd", "width/2 ${width/2} height/2 ${height/2}")

        // draw background
        canvas.drawArc(rectFWillDraw, START_ANGEL, SWEEP_ANGEL, false, progressBackgroundPaint)

        // calculate progress
        val angelByProgressValue = progressValue * SWEEP_ANGEL / 100f

        canvas.drawArc(rectFWillDraw, START_ANGEL, angelByProgressValue, false, progressPaint)

        if (progressValue == 0f) {
            onAnimationStart.invoke(
                0f,
                0f
            )
        }

        val endAngelRadian = Math.toRadians((START_ANGEL + angelByProgressValue).toDouble()).toFloat()

        val topValue = (1 + sin(endAngelRadian)) * (centerX + spaceHorizontalWidthProgress) + spaceTop
        val leftValue = (1 + cos(endAngelRadian)) * (centerX + spaceHorizontalWidthProgress) - progressHalfOfHeight
        onAnimation.invoke(
            topValue,
            leftValue
        )

    }

}