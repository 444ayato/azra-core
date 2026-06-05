package com.example.azra_core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPref = getSharedPreferences("BinaDesaData", Context.MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        // Delay 2,5 detik untuk memutar ProgressBar bawaanmu
        Handler(Looper.getMainLooper()).postDelayed({
            if (isLogin) {
                // Jika user sudah berstatus login, langsung masuk ke Home (MainActivity)
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                // KONDISI DIUBAH: Jika belum login, selalu paksa masuk ke Onboarding Screen
                // setiap kali aplikasi di-run ulang dari Android Studio.
                startActivity(Intent(this, OnboardingActivity::class.java))
            }
            finish()
        }, 2500)
    }
}