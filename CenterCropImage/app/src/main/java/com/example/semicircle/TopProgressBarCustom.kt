package com.example.semicircle

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import com.example.centercropimage.R
import com.example.centercropimage.databinding.TopPreogressBarCustomBinding

class TopProgressBarCustom : FrameLayout {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    val binding: TopPreogressBarCustomBinding = TopPreogressBarCustomBinding.inflate(LayoutInflater.from(context), this, false)
    val ANIMATION_DURATION = 4000
    val PROGRESS_MIN = 0f
    val PROGRESS_MAX = 100f
    private val halfOfImageSize = MyUtils.dpToPx(16f, resources)
    private val imageSize = MyUtils.dpToPx(32f, resources)
    private val imageMarginTop = MyUtils.dpToPx(24f, resources)

    init {
        addView(binding.root)

        // set image
//        binding.imageProgress.setImageResource(R.drawable.center_crop_bg)

        // add event
        binding.dailyProgressBar.onAnimationStart = { topValue, leftValue ->
//            binding.imageProgress.x = leftValue
//            binding.imageProgress.y = topValue
            binding.imageProgress.visibility = VISIBLE
        }
        binding.dailyProgressBar.onAnimation = { topValue, leftValue ->
//            binding.imageProgress.translationX = leftValue
//            binding.imageProgress.translationY = topValue
            binding.imageProgress.x = leftValue
            binding.imageProgress.y = topValue
//            binding.imageProgress.animate()
//                .x(leftValue)
//                .y(leftValue)
//                .start()
        }
        binding.dailyProgressBar.onAnimationEnd = { topValue, leftValue ->
            binding.imageProgress.x = leftValue
            binding.imageProgress.y = topValue
//            binding.imageProgress.animate()
//                .x(leftValue)
//                .y(leftValue)
//                .start()
        }
    }

    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)
        initData()
    }

    fun initData() {
        startAnimationDailyProgressBar(100f)
    }

    fun startAnimationDailyProgressBar(progressValue : Float){
        if (progressValue > PROGRESS_MIN && progressValue <= PROGRESS_MAX) {
            val dailyProgressBarAnimator = ValueAnimator.ofFloat(0f, progressValue)
            dailyProgressBarAnimator.duration = (progressValue * ANIMATION_DURATION / 100f).toLong()
            dailyProgressBarAnimator.interpolator = LinearInterpolator()
            dailyProgressBarAnimator.addUpdateListener {
                binding.dailyProgressBar.setProgressValue(it.animatedValue as Float)
            }
            dailyProgressBarAnimator.start()
        }
    }
}