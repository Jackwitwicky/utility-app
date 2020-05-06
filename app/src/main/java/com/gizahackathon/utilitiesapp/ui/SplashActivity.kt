package com.gizahackathon.utilitiesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.gizahackathon.utilitiesapp.R
import com.gizahackathon.utilitiesapp.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        //splash screen timer
        const val SPLASH_TIME_OUT : Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            val homeIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(homeIntent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}
