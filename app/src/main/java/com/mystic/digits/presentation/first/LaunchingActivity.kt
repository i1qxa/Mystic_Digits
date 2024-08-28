package com.mystic.digits.presentation.first

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.mystic.digits.R
import com.mystic.digits.databinding.ActivityLaunchingBinding
import com.mystic.digits.presentation.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LaunchingActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLaunchingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        fetchUpdates()
    }

    private fun fetchUpdates(){
        lifecycleScope.launch {
            var schetchik = 15
            var dots = binding.tvLoadingD.text.toString()
            val tvDots = binding.tvLoadingD
            while (schetchik>0){
                delay(300)
                dots = updateDots(dots)
                tvDots.text = dots
                schetchik--
            }
            launchGame()
        }
    }

    private fun updateDots(dots:String):String{
        return when(dots){
            "." -> ".."
            ".." -> "..."
            "..." -> "...."
            else -> "."
        }
    }

    private fun launchGame(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}