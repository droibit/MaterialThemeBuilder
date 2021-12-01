package com.github.droibit.sample.materialthemebuilder.ui.component

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.github.droibit.sample.materialthemebuilder.R
import com.github.droibit.sample.materialthemebuilder.ui.component.Component.*
import com.github.droibit.sample.materialthemebuilder.ui.component.ComponentAdapter.ComponentAdapterListener
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


/**
 * Sealed class to define all [RecyclerView.ViewHolder]s used to display [Component]s.
 */
sealed class ComponentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(component: Component) = Unit

    class ButtonComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_buttons))

    class FabComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_fabs))

    class CardComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_cards))

    class TopAppBarComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_top_app_bar))

    class ChipComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_chips))

    class DrawerComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_drawer)) {
        private val drawerLayout: DrawerLayout = itemView.findViewById(R.id.drawer_layout)
        private val navigationView: NavigationView = itemView.findViewById(R.id.nav_view)

        override fun bind(component: Component) {
            drawerLayout.openDrawer(Gravity.LEFT)
            navigationView.setNavigationItemSelectedListener { true }
            navigationView.setCheckedItem(R.id.nav_item_one)
        }
    }

    class BottomNavigationComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_bottom_navigation))

    class TextFieldComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_text_field))

    class SwitchComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_switch))

    class RadioButtonComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_radio_button))

    class CheckboxComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_checkbox))

    class BottomAppBarComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_bottom_app_bar)) {
        private val bottomAppBar: BottomAppBar = itemView.findViewById(R.id.bottom_app_bar)

        override fun bind(component: Component) {
            bottomAppBar.overflowIcon = ContextCompat.getDrawable(
                bottomAppBar.context,
                R.drawable.ic_more_vert_on_surface_24dp
            )
        }
    }

    // TODO: It is not displayed after migrating to MD3.
    class TabsComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_tabs))

    class SnackbarComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_snackbar)) {
        init {
            val container: FrameLayout = itemView.findViewById(R.id.snackbar_container)
            val snackbarView = Snackbar.make(
                container,
                R.string.snackbar_message_text,
                Snackbar.LENGTH_INDEFINITE
            ).setAction(R.string.snackbar_action_text) {
            }.view
            (snackbarView.layoutParams as FrameLayout.LayoutParams).gravity = Gravity.CENTER
            container.addView(snackbarView)
        }
    }

    class DialogComponentViewHolder(
        parent: ViewGroup
    ) : ComponentViewHolder(inflate(parent, R.layout.component_dialog)) {
        init {
            val button = itemView.findViewById<MaterialButton>(R.id.button)
            button.setOnClickListener {
                showDialog()
            }
        }

        private fun showDialog() {
            MaterialAlertDialogBuilder(itemView.context)
                .setTitle(R.string.text_headline_6)
                .setMessage(R.string.lorem_ipsum)
                .setPositiveButton(R.string.text_button, null)
                .setNegativeButton(R.string.text_button, null)
                .show()
        }
    }

    class BottomSheetComponentViewHolder(
        parent: ViewGroup,
        listener: ComponentAdapter.ComponentAdapterListener
    ) : ComponentViewHolder(inflate(parent, R.layout.component_bottom_sheet)) {
        init {
            itemView.findViewById<MaterialButton>(R.id.button).setOnClickListener {
                listener.onShowBottomSheetClicked()
            }
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            viewType: Int,
            listener: ComponentAdapterListener
        ): ComponentViewHolder {
            return when (Component.getBy(viewType)) {
                BUTTON -> ButtonComponentViewHolder(parent)
                FAB -> FabComponentViewHolder(parent)
                CARD -> CardComponentViewHolder(parent)
                TOP_APP_BAR -> TopAppBarComponentViewHolder(parent)
                CHIP -> ChipComponentViewHolder(parent)
                DRAWER -> DrawerComponentViewHolder(parent)
                TEXT_FIELD -> TextFieldComponentViewHolder(parent)
                BOTTOM_NAVIGATION -> BottomNavigationComponentViewHolder(parent)
                SWITCH -> SwitchComponentViewHolder(parent)
                RADIO_BUTTON -> RadioButtonComponentViewHolder(parent)
                CHECKBOX -> CheckboxComponentViewHolder(parent)
                BOTTOM_APP_BAR -> BottomAppBarComponentViewHolder(parent)
                TABS -> TabsComponentViewHolder(parent)
                SNACKBAR -> SnackbarComponentViewHolder(parent)
                DIALOG -> DialogComponentViewHolder(parent)
                BOTTOM_SHEET -> BottomSheetComponentViewHolder(parent, listener)
            }
        }

        private fun inflate(parent: ViewGroup, layout: Int): View {
            return LayoutInflater.from(parent.context).inflate(layout, parent, false)
        }
    }
}