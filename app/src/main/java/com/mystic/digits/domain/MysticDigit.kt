package com.mystic.digits.domain

import android.util.TypedValue

data class MysticDigit(
    val view:OutlineTextView,
    val value:Short,
    val x:Short,
    val y:Short
){
    fun setupView() {
        if (value>0){
            view.text = value.toString()
            view.setOutlineWidth(TypedValue.COMPLEX_UNIT_PX, 2f)
        }
    }

    fun checkPosition():Boolean{
        return ((y*4)+x).toShort() == value
    }

}
