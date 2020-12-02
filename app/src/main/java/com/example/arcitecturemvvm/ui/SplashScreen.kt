package com.example.arcitecturemvvm.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.arcitecturemvvm.R
import com.example.arcitecturemvvm.util.loadAnimation

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashAnimator = loadAnimation(this@SplashScreen, R.anim.splash_animation)

        val splashImage: ImageView = findViewById(R.id.splashImage)
        val splashText: TextView = findViewById(R.id.splashText)

        splashImage.startAnimation(splashAnimator)
        splashText.startAnimation(splashAnimator)


        //running on thread
        val timer = object : Thread() {
            override fun run() {
                try {
                    sleep(4000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                    finish()
                }
            }
        }
        timer.start()

    }
}