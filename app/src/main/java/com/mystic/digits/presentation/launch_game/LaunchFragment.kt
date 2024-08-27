package com.mystic.digits.presentation.launch_game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.mystic.digits.databinding.FragmentLaunchBinding
import com.mystic.digits.domain.launchWith
import com.mystic.digits.presentation.exit.ExitFragment
import com.mystic.digits.presentation.level.LevelFragment
import com.mystic.digits.presentation.options.OptionsFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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


    private fun setupBtnsClickListener() {
        binding.exit.setOnClickListener {
            parentFragmentManager.launchWith(ExitFragment())
        }
        binding.options.setOnClickListener {
            parentFragmentManager.launchWith(OptionsFragment())
        }
        binding.levels.setOnClickListener {
            parentFragmentManager.launchWith(LevelFragment())
        }
    }

}