package com.example.azra_core

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailProyekDisetujuiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_proyek_disetujui)

        val tvDetailTitle = findViewById<TextView>(R.id.tvDetailTitle)
        val btnBack = findViewById<Button>(R.id.btnBackToHome)

        // Ambil lemparan data nama proyek dari system notification reminder
        val namaProyek = intent.getStringExtra("EXTRA_NAMA_PROYEK") ?: "Proyek Tidak Diketahui"

        tvDetailTitle.text = "Lokasi Proyek:\n$namaProyek\nTELAH DISETUJUI!"

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}