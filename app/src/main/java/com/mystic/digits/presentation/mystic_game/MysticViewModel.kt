package com.mystic.digits.presentation.mystic_game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mystic.digits.domain.MysticDigit
import com.mystic.digits.domain.OutlineTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MysticViewModel : ViewModel() {

    private val listOfDigits = mutableListOf<Short>(
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15
    ).shuffled()
    private val listOfMysticDigits = mutableListOf<MysticDigit>()
    private var isAnimInProgress = false
    private var timerScope:CoroutineScope? = null
    val timer = MutableLiveData<Short>()
    val finishGame = MutableLiveData<Any>()
    val playSoundLD = MutableLiveData<Any>()

    init {
        viewModelScope.launch {
            timerScope = this
            while (timerScope!=null){
                delay(1000)
                val currentTime = (timer.value?:0) + 1
                timer.postValue(currentTime.toShort())
            }

        }
    }

    fun setupMysticDigits(listViews:List<OutlineTextView>){
        var x:Short = 1
        var y:Short = 0
        var index = 0
        repeat(listOfDigits.size){
            val md = MysticDigit(listViews[index], listOfDigits[index], x, y)
            listOfMysticDigits.add(md)
            index++
            x++
            if (x>4){
                x=1
                y++
            }
        }
        listOfMysticDigits.map {
            it.setupView()
        }
    }

    private fun checkRes(){
        var res = true
        var index = 0
        while (index<(listOfMysticDigits.size - 1)){
            Log.d("INDEX", "index: $index")
            if (!listOfMysticDigits[index].checkPosition()) res = false
            index++
        }
//        listOfMysticDigits.map {
//            if (!it.checkPosition()) res = false
//        }
        if (res){
            stopTimer()
            finishGame.postValue(Unit)
        }
    }

    fun tryToMoveDigit(view:OutlineTextView){
        val clickedMD = getMysticDigitByView(view)
        val targetMD = findEmptyDigit(clickedMD)
        if(targetMD!=null&&!isAnimInProgress){
            clickedMD.view.animate().apply {
                isAnimInProgress = true
                duration = 500
                x(targetMD.view.x)
                y(targetMD.view.y)
            }
            targetMD.view.animate().apply {
                duration = 500
                x(clickedMD.view.x)
                y(clickedMD.view.y)
                withEndAction {
                    isAnimInProgress = false
                }
            }
            listOfMysticDigits.remove(clickedMD)
            listOfMysticDigits.remove(targetMD)
            listOfMysticDigits.add(clickedMD.copy(x = targetMD.x, y = targetMD.y))
            listOfMysticDigits.add(targetMD.copy(x = clickedMD.x, y = clickedMD.y))
            playSoundLD.value = Unit
            checkRes()
        }
    }

    fun stopTimer(){
        timerScope?.cancel()
    }

    private fun getMysticDigitByPosition(x:Short, y:Short):MysticDigit?{
        return try {
            listOfMysticDigits.filter { it.x == x && it.y == y }[0]
        }catch (e:IndexOutOfBoundsException){
            null
        }
    }

    private fun getMysticDigitByView(view:OutlineTextView):MysticDigit{
        return listOfMysticDigits.filter { it.view == view }[0]
    }

    private fun findEmptyDigit(clickedMD: MysticDigit):MysticDigit?{
        var targetMD = getMysticDigitByPosition((clickedMD.x - 1).toShort(), clickedMD.y)
        if (targetMD!=null && targetMD.value== 0.toShort()) return targetMD
        targetMD = getMysticDigitByPosition((clickedMD.x + 1).toShort(), clickedMD.y)
        if (targetMD!=null && targetMD.value== 0.toShort()) return targetMD
        targetMD = getMysticDigitByPosition(clickedMD.x, (clickedMD.y - 1).toShort())
        if (targetMD!=null && targetMD.value== 0.toShort()) return targetMD
        targetMD = getMysticDigitByPosition(clickedMD.x, (clickedMD.y + 1).toShort())
        if (targetMD!=null && targetMD.value== 0.toShort()) return targetMD
        else return null
    }


}