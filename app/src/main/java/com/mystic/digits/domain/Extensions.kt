package com.mystic.digits.domain

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.mystic.digits.R

const val prefsName = "mystic_digits_prefs"
const val soundPrefs = "sound"

fun FragmentManager.launchWith(fragment:Fragment){
    this.beginTransaction().apply {
        replace(R.id.conteinerMystic, fragment)
        addToBackStack(null)
        commit()
    }
}

fun FragmentManager.launchWithOut(fragment:Fragment){
    this.beginTransaction().apply {
        replace(R.id.conteinerMystic, fragment)
        commit()
    }
}