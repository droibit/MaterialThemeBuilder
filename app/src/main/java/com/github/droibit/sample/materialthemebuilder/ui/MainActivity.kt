package com.github.droibit.sample.materialthemebuilder.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import com.github.droibit.sample.materialthemebuilder.App
import com.github.droibit.sample.materialthemebuilder.data.PreferenceRepository
import com.github.droibit.sample.materialthemebuilder.databinding.ActivityMainBinding
import com.github.droibit.sample.materialthemebuilder.preferenceRepository
import com.github.droibit.sample.materialthemebuilder.ui.MainViewPagerAdapter.MainFragments
import com.google.android.material.tabs.TabLayoutMediator
import dev.chrisbanes.insetter.applyInsetter
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBar.applyInsetter {
            type(statusBars = true) {
                padding()
            }
        }
        binding.viewPager.applyInsetter {
            type(navigationBars = true) {
                padding(bottom = true)
            }
        }

        binding.viewPager.adapter = MainViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setText(MainFragments.getBy(index = position).titleRes)
        }.attach()

        preferenceRepository.nightModeLive.observe(this, Observer { nightMode ->
            nightMode?.let { delegate.localNightMode = it }
        })
    }
}