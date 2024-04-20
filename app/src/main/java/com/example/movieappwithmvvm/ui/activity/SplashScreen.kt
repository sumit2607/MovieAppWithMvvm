package com.example.movieappwithmvvm.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieappwithmvvm.R

class SplashScreen : AppCompatActivity() {
    // private lateinit var binding: ActivitySplashScreenBinding
    private val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding = ActivitySplashScreenBinding.inflate()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        handler.postDelayed({
            // Create an Intent to start SecondActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 3000) // 3000 milliseconds = 3 seconds
    }


    override fun onDestroy() {
        super.onDestroy()
        // Remove any pending delayed tasks to avoid memory leaks
        handler.removeCallbacksAndMessages(null)
    }
}