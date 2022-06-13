package com.example.android.materialthemebuilder.ui

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.android.materialthemebuilder.R
import com.example.android.materialthemebuilder.ui.component.ComponentFragment
import com.example.android.materialthemebuilder.ui.instruction.InstructionsFragment
import com.example.android.materialthemebuilder.ui.themesummary.ThemeSummaryFragment

/**
 * View pager to show all tabbed destinations - Instructions, Theme Summary and Components.
 */
class MainViewPagerAdapter(
    activity: MainActivity
) : FragmentStateAdapter(activity) {

    enum class MainFragments(@StringRes val titleRes: Int) {
        INSTRUCTIONS(R.string.tab_title_instructions),
        THEME_SUMMARY(R.string.tab_title_theme_summary),
        COMPONENTS(R.string.tab_title_components);

        companion object {
            fun getBy(index: Int) = values()[index]
        }
    }

    override fun getItemCount(): Int = MainFragments.values().size

    override fun createFragment(position: Int): Fragment {
        return when (MainFragments.getBy(position)) {
            MainFragments.INSTRUCTIONS -> InstructionsFragment()
            MainFragments.THEME_SUMMARY -> ThemeSummaryFragment()
            MainFragments.COMPONENTS -> ComponentFragment()
        }
    }
}