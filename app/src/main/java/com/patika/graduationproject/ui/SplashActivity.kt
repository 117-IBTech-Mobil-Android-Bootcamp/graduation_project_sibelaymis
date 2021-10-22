package com.patika.graduationproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.patika.graduationproject.R
import com.patika.graduationproject.startActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
    private val DELAY  :  Long = 2 * 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.window.statusBarColor=resources.getColor(R.color.white)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity<HomeActivity>()
                finish()
            }
        }, DELAY)
    }
}