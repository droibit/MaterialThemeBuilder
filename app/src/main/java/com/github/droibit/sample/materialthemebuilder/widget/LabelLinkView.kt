package com.github.droibit.sample.materialthemebuilder.widget

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes
import androidx.core.net.toUri
import androidx.core.view.isGone
import com.github.droibit.sample.materialthemebuilder.R

/**
 * Composite view to show an (optional) leading icon, followed by a text label, followed by
 * a trailing icon.
 *
 * Clicking on this view's trailing icon will launch [linkUrl].
 */
class LabelLinkView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val leadingImageView: AppCompatImageView
    private val labelTextView: AppCompatTextView
    private val trailingImageView: AppCompatImageView

    private var leadingIcon: Drawable? = null
        set(value) {
            if (value == null) {
                leadingImageView.isGone = true
            } else {
                leadingImageView.setImageDrawable(value)
                leadingImageView.isGone = false
            }
            field = value
        }

    private var label: String = ""
        set(value) {
            labelTextView.text = value
            field = value
        }

    private var linkUrl: String = ""

    private val onLinkClickedListener = OnClickListener {
        if (linkUrl.isBlank()) return@OnClickListener
        launchUrl(linkUrl)
    }

    init {
        clipToPadding = false
        orientation = LinearLayout.HORIZONTAL

        val view = View.inflate(context, R.layout.label_view_layout, this)
        leadingImageView = view.findViewById(R.id.label_leading_image_view)
        labelTextView = view.findViewById(R.id.label_text_view)
        trailingImageView = view.findViewById(R.id.label_trailing_image_view)
        trailingImageView.setOnClickListener(onLinkClickedListener)

        context.withStyledAttributes(attrs, R.styleable.LabelLinkView, defStyleAttr, defStyleRes) {
            leadingIcon = getDrawable(R.styleable.LabelLinkView_leadingIcon)
            label = getString(R.styleable.LabelLinkView_android_text) ?: label
            linkUrl = getString(R.styleable.LabelLinkView_linkUrl) ?: linkUrl
        }
    }

    private fun launchUrl(urlString: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, urlString.toUri()))
    }
}