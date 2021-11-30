package com.github.droibit.sample.materialthemebuilder.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes
import com.github.droibit.sample.materialthemebuilder.R

/**
 * Composite view to show an item containing a text label and a [ColorDotView].
 */
class ColorAttributeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val colorAttributeTextView: AppCompatTextView
    private val colorDowView: ColorDotView

    private var attributeText: String = ""
        set(value) {
            colorAttributeTextView.text = value
            field = value
        }

    @ColorInt
    private var dotFillColor: Int = Color.LTGRAY
        set(value) {
            colorDowView.fillColor = value
            field = value
        }

    @ColorInt
    private var dotStrokeColor: Int = Color.DKGRAY
        set(value) {
            colorDowView.strokeColor = value
            field = value
        }

    init {
        val view = View.inflate(context, R.layout.color_attribute_view_layout, this)
        colorAttributeTextView = view.findViewById(R.id.color_attribute)
        colorDowView = view.findViewById(R.id.color_dot)

        context.withStyledAttributes(
            attrs,
            R.styleable.ColorAttributeView,
            defStyleAttr,
            defStyleRes
        ) {
            attributeText = getString(R.styleable.ColorAttributeView_android_text) ?: attributeText
            dotFillColor = getColor(R.styleable.ColorAttributeView_colorFillColor, dotFillColor)
            dotStrokeColor =
                getColor(R.styleable.ColorAttributeView_colorStrokeColor, dotStrokeColor)
        }
    }
}