package com.mystic.digits.presentation.level

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mystic.digits.R
import com.mystic.digits.databinding.FragmentLevelBinding
import com.mystic.digits.domain.launchWith
import com.mystic.digits.domain.lvlPrefs
import com.mystic.digits.domain.prefsName
import com.mystic.digits.presentation.mystic_game.MysticGameFragment

class LevelFragment : Fragment() {

    private val binding by lazy { FragmentLevelBinding.inflate(layoutInflater) }
    private val prefs by lazy {
        requireContext().getSharedPreferences(
            prefsName,
            Context.MODE_PRIVATE
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
        setupLvlDigits()
        setupStars()
        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupLvlDigits() {
        binding.lvl1.digit.setImageResource(R.drawable.d_1)
        binding.lvl2.digit.setImageResource(R.drawable.d_2)
        binding.lvl3.digit.setImageResource(R.drawable.d_3)
        binding.lvl4.digit.setImageResource(R.drawable.d_4)
        binding.lvl5.digit.setImageResource(R.drawable.d_5)
        binding.lvl6.digit.setImageResource(R.drawable.d_6)
        binding.lvl7.digit.setImageResource(R.drawable.d_7)
        binding.lvl8.digit.setImageResource(R.drawable.d_8)
        binding.lvl9.digit.setImageResource(R.drawable.d_9)
        binding.lvl10.digit.setImageResource(R.drawable.d_10)
        binding.lvl11.digit.setImageResource(R.drawable.d_11)
        binding.lvl12.digit.setImageResource(R.drawable.d_12)
        binding.lvl13.digit.setImageResource(R.drawable.d_13)
        binding.lvl14.digit.setImageResource(R.drawable.d_14)
        binding.lvl15.digit.setImageResource(R.drawable.d_15)
    }

    private fun setupStars() {
        val lastLvl = prefs.getInt(lvlPrefs, 1)
        var currentLvl = 1
        val yellowStars = R.drawable.all_stars
        if (currentLvl <= lastLvl) {
            binding.lvl1.stars.setImageResource(yellowStars)
            binding.lvl1.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl2.stars.setImageResource(yellowStars)
            binding.lvl2.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl3.stars.setImageResource(yellowStars)
            binding.lvl3.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl4.stars.setImageResource(yellowStars)
            binding.lvl4.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl5.stars.setImageResource(yellowStars)
            binding.lvl5.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl6.stars.setImageResource(yellowStars)
            binding.lvl6.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl7.stars.setImageResource(yellowStars)
            binding.lvl7.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl8.stars.setImageResource(yellowStars)
            binding.lvl8.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl9.stars.setImageResource(yellowStars)
            binding.lvl9.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl10.stars.setImageResource(yellowStars)
            binding.lvl10.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl11.stars.setImageResource(yellowStars)
            binding.lvl11.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl12.stars.setImageResource(yellowStars)
            binding.lvl12.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl13.stars.setImageResource(yellowStars)
            binding.lvl13.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl14.stars.setImageResource(yellowStars)
            binding.lvl14.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
        currentLvl++
        if (currentLvl <= lastLvl) {
            binding.lvl15.stars.setImageResource(yellowStars)
            binding.lvl15.root.setOnClickListener {
                parentFragmentManager.launchWith(MysticGameFragment())
            }
        }
    }

}