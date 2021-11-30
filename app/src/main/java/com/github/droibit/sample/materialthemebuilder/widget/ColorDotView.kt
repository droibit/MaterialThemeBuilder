package com.github.droibit.sample.materialthemebuilder.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import com.github.droibit.sample.materialthemebuilder.R

/**
 * Simple view that draws a filled circle with a stroke.
 */
class ColorDotView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    @ColorInt
    var fillColor: Int = Color.GRAY
        set(value) {
            paintFill.color = value
            field = value
        }

    @ColorInt
    var strokeColor: Int = Color.DKGRAY
        set(value) {
            paintStroke.color = value
            field = value
        }

    private val paintFill = Paint(ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED
    }

    private val paintStroke = Paint(ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        color = Color.RED
    }

    private var cx: Float = 0f
    private var cy: Float = 0f
    private var radius: Float = 0f

    init {
        context.withStyledAttributes(attrs, R.styleable.ColorDotView, defStyleAttr) {
            fillColor = getColor(R.styleable.ColorDotView_colorFillColor, fillColor)
            strokeColor = getColor(R.styleable.ColorDotView_colorStrokeColor, strokeColor)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cx = w / 2f
        cy = h / 2f
        // Decreases our circle's radius slightly so our stroke doesn't get clipped.
        radius = (w / 2f) - 1f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(cx, cy, radius, paintFill)
        canvas.drawCircle(cx, cy, radius, paintStroke)
    }
}