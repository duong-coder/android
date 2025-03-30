package com.example.semicircle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
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

    init {
        addView(binding.root)
    }
}