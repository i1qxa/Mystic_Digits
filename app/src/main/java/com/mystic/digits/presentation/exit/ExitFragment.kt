package com.mystic.digits.presentation.exit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mystic.digits.R
import com.mystic.digits.databinding.FragmentExitBinding
import com.mystic.digits.domain.launchWithOut

class ExitFragment : Fragment() {

    private val binding by lazy { FragmentExitBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.yes.setOnClickListener {
            requireActivity().finishAffinity()
        }
        binding.no.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}