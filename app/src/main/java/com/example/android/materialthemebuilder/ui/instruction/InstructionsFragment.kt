package com.example.android.materialthemebuilder.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.android.materialthemebuilder.databinding.FragmentInstructionsBinding
import com.example.android.materialthemebuilder.preferenceRepository

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceRepository.isDarkThemeLive.observe(viewLifecycleOwner, Observer { isDarkTheme ->
            isDarkTheme?.let { binding.darkThemeSwitch.isChecked = it }
        })

        binding.darkThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            preferenceRepository.isDarkTheme = isChecked
        }
    }
}