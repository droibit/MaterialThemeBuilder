package com.github.droibit.sample.materialthemebuilder.ui.themesummary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.github.droibit.sample.materialthemebuilder.R

sealed class SubsystemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    open fun bind(subsytem: Subsystem) = Unit

    class ColorSybsystemViewHolder(
        parent: ViewGroup
    ) : SubsystemViewHolder(inflate(parent, R.layout.subsystem_color))

    class TypeSubsystemViewHolder(
        parent: ViewGroup
    ) : SubsystemViewHolder(inflate(parent, R.layout.subsystem_type))

    class ShapeSubsystemViewHolder(
        parent: ViewGroup
    ) : SubsystemViewHolder(inflate(parent, R.layout.subsystem_shape))

    companion object {

        fun create(parent: ViewGroup, viewType: Int): SubsystemViewHolder {
            return when (Subsystem.getBy(viewType)) {
                Subsystem.COLOR -> ColorSybsystemViewHolder(parent)
                Subsystem.TYPE -> TypeSubsystemViewHolder(parent)
                Subsystem.SHAPE -> ShapeSubsystemViewHolder(parent)
            }
        }

        private fun inflate(parent: ViewGroup, @LayoutRes layout: Int): View {
            return LayoutInflater.from(parent.context).inflate(layout, parent, false)
        }
    }
}