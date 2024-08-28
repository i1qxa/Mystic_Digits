package com.mystic.digits.presentation.result

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mystic.digits.R
import com.mystic.digits.databinding.FragmentMysticResultBinding
import com.mystic.digits.domain.launchWithOut
import com.mystic.digits.domain.prefsName
import com.mystic.digits.domain.soundPrefs
import com.mystic.digits.presentation.launch_game.LaunchFragment
import com.mystic.digits.presentation.mystic_game.MysticGameFragment

class MysticResultFragment : Fragment() {

    private var mysticPlayer: MediaPlayer? = null
    private val prefs by lazy {
        requireContext().getSharedPreferences(
            prefsName,
            Context.MODE_PRIVATE
        )
    }
    private val binding by lazy { FragmentMysticResultBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.home.setOnClickListener {
            parentFragmentManager.launchWithOut(LaunchFragment())
        }
        binding.next.setOnClickListener {
            parentFragmentManager.launchWithOut(MysticGameFragment())
        }
        if (prefs.getBoolean(soundPrefs, true)){
            playFanfari()
        }
    }

    private fun playFanfari() {
        if (mysticPlayer == null) {
            mysticPlayer = MediaPlayer.create(requireContext(), R.raw.fanfary)
            mysticPlayer!!.isLooping = false
            mysticPlayer!!.start()
        } else mysticPlayer!!.start()
    }

}