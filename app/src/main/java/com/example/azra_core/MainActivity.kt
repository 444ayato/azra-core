package com.example.azra_core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengarah kembali ke activity_main
        setContentView(R.layout.activity_main)

        // Setup Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Bina Desa Dashboard"

        // Tombol pemicu WebView
        val btnWeb = findViewById<Button>(R.id.btnWebView)
        btnWeb.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // Tombol Logout
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            // Hapus data login di SharedPreferences
            val sharedPref = getSharedPreferences("TugasBinaDesa", Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()

            Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show()

            // Kembali ke LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            // Menutup MainActivity agar tidak bisa kembali ke sini dengan tombol Back
            finish()
        }
    }
}