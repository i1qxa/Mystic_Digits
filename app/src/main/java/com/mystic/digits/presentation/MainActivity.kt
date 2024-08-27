package com.mystic.digits.presentation

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
import com.mystic.digits.R
import com.mystic.digits.domain.launchWith
import com.mystic.digits.presentation.exit.ExitFragment
import com.mystic.digits.presentation.launch_game.LaunchFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.findFragmentById(R.id.conteinerMystic) is LaunchFragment) {
                    supportFragmentManager.launchWith(ExitFragment())
                }else {
                    supportFragmentManager.popBackStack()
                }
            }
        }
        this.onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

}