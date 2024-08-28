package com.mystic.digits.presentation.mystic_game

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mystic.digits.R
import com.mystic.digits.databinding.FragmentMysticGameBinding
import com.mystic.digits.domain.launchWithOut
import com.mystic.digits.domain.lvlPrefs
import com.mystic.digits.domain.prefsName
import com.mystic.digits.domain.soundPrefs
import com.mystic.digits.presentation.options.OptionsFragment
import com.mystic.digits.presentation.result.MysticResultFragment

class MysticGameFragment : Fragment() {

    private val binding by lazy { FragmentMysticGameBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MysticViewModel>()
    private val prefs by lazy {
        requireContext().getSharedPreferences(
            prefsName,
            Context.MODE_PRIVATE
        )
    }
    private var mysticPlayer: MediaPlayer? = null
    private val listOfGameViews by lazy {
        listOf(
            binding.d1,
            binding.d2,
            binding.d3,
            binding.d4,
            binding.d5,
            binding.d6,
            binding.d7,
            binding.d8,
            binding.d9,
            binding.d10,
            binding.d11,
            binding.d12,
            binding.d13,
            binding.d14,
            binding.d15,
            binding.d16,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeTvLvl()
        setupGame()
        observeVM()
        setupBtnClicks()
    }

    private fun playMoveSound() {
        if (mysticPlayer == null) {
            mysticPlayer = MediaPlayer.create(requireContext(), R.raw.move_sound)
            mysticPlayer!!.isLooping = false
            mysticPlayer!!.start()
        } else mysticPlayer!!.start()
    }

    private fun stopSound() {
        if (mysticPlayer != null) {
            mysticPlayer!!.stop()
            mysticPlayer!!.release()
            mysticPlayer = null
        }
    }

    private fun setupBtnClicks(){
        binding.back.setOnClickListener {
            viewModel.stopTimer()
            parentFragmentManager.popBackStack()
        }
        binding.settings.setOnClickListener {
            viewModel.stopTimer()
            parentFragmentManager.launchWithOut(OptionsFragment())
        }
    }

    private fun setupGame() {
        viewModel.setupMysticDigits(listOfGameViews)
        setupOnClickListener()
    }

    @SuppressLint("DefaultLocale")
    private fun observeVM() {
        viewModel.finishGame.observe(viewLifecycleOwner) {
            val lvl = prefs.getInt(lvlPrefs, 1) + 1
            prefs.edit().putInt(lvlPrefs, lvl).apply()
            parentFragmentManager.launchWithOut(MysticResultFragment())
        }
        viewModel.timer.observe(viewLifecycleOwner) {
            binding.tvTimer.text = String.format("%02d:%02d", it / 60, it % 60)
        }
        viewModel.playSoundLD.observe(viewLifecycleOwner){
            if (prefs.getBoolean(soundPrefs, true)) {
                stopSound()
                playMoveSound()
            }
        }
    }

    private fun setupOnClickListener() {
        listOfGameViews.map { outlineTextView ->
            outlineTextView.setOnClickListener {
                viewModel.tryToMoveDigit(outlineTextView)
            }
        }
    }

    private fun makeTvLvl() {
        val lvl = prefs.getInt(lvlPrefs, 1).toString()
        val lvlText = getString(R.string.level, lvl)
        binding.tvLevel.text = lvlText
        binding.tvLeve2.text = lvlText
        binding.tvLeve3.text = lvlText
        binding.tvLeve4.text = lvlText
        binding.tvLevel.paint.setShadowLayer(4F, 4F, 0F, Color.RED)
        binding.tvLeve2.paint.setShadowLayer(4F, -4F, 0F, Color.RED)
        binding.tvLeve3.paint.setShadowLayer(4F, 0F, 4F, Color.RED)
        binding.tvLeve4.paint.setShadowLayer(4F, 0F, -4F, Color.RED)
    }

}