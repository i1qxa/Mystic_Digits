package com.mystic.digits.presentation.launch_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mystic.digits.databinding.FragmentLaunchBinding
import com.mystic.digits.domain.launchWith
import com.mystic.digits.presentation.options.OptionsFragment

class LaunchFragment : Fragment() {

    private val binding by lazy { FragmentLaunchBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtnsClickListener()
    }

    private fun setupBtnsClickListener(){
        binding.options.setOnClickListener {
            parentFragmentManager.launchWith(OptionsFragment())
        }
    }

}