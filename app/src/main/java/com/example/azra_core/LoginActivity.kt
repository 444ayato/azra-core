package com.example.azra_core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            // Logika simpan SharedPreferences
            val sharedPref = getSharedPreferences("TugasBinaDesa", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("isLogin", true).apply()

            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}