package com.mystic.digits.presentation.options

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.mystic.digits.R
import com.mystic.digits.databinding.FragmentOptionsBinding
import com.mystic.digits.domain.dataStore
import com.mystic.digits.domain.musicKey
import com.mystic.digits.domain.prefsName
import com.mystic.digits.domain.soundPrefs
import kotlinx.coroutines.launch

class OptionsFragment : Fragment() {

    private val binding by lazy { FragmentOptionsBinding.inflate(layoutInflater) }
    private val prefs by lazy {
        requireContext().getSharedPreferences(
            prefsName,
            Context.MODE_PRIVATE
        )
    }
    private var isSound = true
    private var isMusic = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchSoundSettings()
        fetchMusicSettings()
        setupBtnClickListeners()
    }

    private fun setupBtnClickListeners() {
        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.soundLogo.setOnClickListener {
            isSound = !isSound
            prefs.edit().putBoolean(soundPrefs, isSound).apply()
            fetchSoundSettings()
        }
        binding.musicLogo.setOnClickListener {
            lifecycleScope.launch {
                requireContext().dataStore.edit {
                    isMusic = !isMusic
                    it[musicKey] = isMusic
                }
            }
        }
    }

    private fun fetchSoundSettings() {
        isSound = prefs.getBoolean(soundPrefs, true)
        binding.tvSound.setTextColor(getTextColor(isSound))
    }

    private fun fetchMusicSettings() {
        requireContext().dataStore.data.asLiveData().observe(viewLifecycleOwner) {
            isMusic = it[musicKey] ?: true
            binding.tvMusic.setTextColor(getTextColor(isMusic))
        }
    }

    private fun getTextColor(isActive: Boolean): Int {
        return if (isActive) requireContext().getColor(R.color.option_active) else requireContext().getColor(
            R.color.option_in_active
        )
    }

}