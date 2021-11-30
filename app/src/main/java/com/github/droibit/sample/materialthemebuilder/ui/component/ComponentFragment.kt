package com.github.droibit.sample.materialthemebuilder.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.droibit.sample.materialthemebuilder.databinding.FragmentComponentBinding
import com.github.droibit.sample.materialthemebuilder.ui.component.BottomSheetFragment.Companion.FRAGMENT_TAG
import com.github.droibit.sample.materialthemebuilder.ui.component.ComponentAdapter.ComponentAdapterListener

class ComponentFragment : Fragment(), ComponentAdapterListener {

    private lateinit var binding: FragmentComponentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComponentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ComponentAdapter(this)
        binding.recyclerView.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
            adapter.submitList(Component.values().toList())
        }
    }

    override fun onShowBottomSheetClicked() {
        BottomSheetFragment()
            .show(requireFragmentManager(), FRAGMENT_TAG)
    }
}