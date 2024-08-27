package com.mystic.digits.presentation.mystic_game

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mystic.digits.R
import com.mystic.digits.databinding.FragmentMysticGameBinding
import com.mystic.digits.domain.lvlPrefs
import com.mystic.digits.domain.prefsName

class MysticGameFragment : Fragment() {

    private val binding by lazy { FragmentMysticGameBinding.inflate(layoutInflater) }
    private val prefs by lazy { requireContext().getSharedPreferences(prefsName, Context.MODE_PRIVATE) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeTvLvl()
    }

    private fun makeTvLvl(){
        val lvl = prefs.getInt(lvlPrefs, 1).toString()
        val lvlText = getString(R.string.level, lvl)
        binding.tvLevel.text = lvlText
        binding.tvLeve2.text = lvlText
        binding.tvLeve3.text = lvlText
        binding.tvLeve4.text = lvlText
        binding.tvLevel.paint.setShadowLayer(4F, 4F,0F, Color.RED)
        binding.tvLeve2.paint.setShadowLayer(4F, -4F,0F, Color.RED)
        binding.tvLeve3.paint.setShadowLayer(4F, 0F,4F, Color.RED)
        binding.tvLeve4.paint.setShadowLayer(4F, 0F,-4F, Color.RED)
    }

}