package com.mystic.digits.presentation

import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.mystic.digits.R
import com.mystic.digits.domain.dataStore
import com.mystic.digits.domain.launchWith
import com.mystic.digits.domain.launchWithOut
import com.mystic.digits.domain.musicKey
import com.mystic.digits.presentation.exit.ExitFragment
import com.mystic.digits.presentation.launch_game.LaunchFragment
import com.mystic.digits.presentation.options.OptionsFragment

class MainActivity : AppCompatActivity() {

    private var mysticPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                when (supportFragmentManager.findFragmentById(R.id.conteinerMystic)) {
                    is LaunchFragment -> supportFragmentManager.launchWith(ExitFragment())
                    is OptionsFragment -> supportFragmentManager.launchWithOut(LaunchFragment())
                    else -> supportFragmentManager.popBackStack()
                }
            }
        }
        this.onBackPressedDispatcher.addCallback(
            this,
            callback
        )
        dataStore.data.asLiveData().observe(this){
            if (it[musicKey] != false){
                playMusic()
            }else{
                stopSound()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if(mysticPlayer!=null){
            mysticPlayer!!.release()
            mysticPlayer=null
        }
    }

    private fun playMusic() {
        if (mysticPlayer == null) {
            mysticPlayer = MediaPlayer.create(this, R.raw.mystic_music)
            mysticPlayer!!.isLooping = true
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

}