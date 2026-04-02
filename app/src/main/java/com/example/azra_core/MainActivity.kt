package com.example.azra_core

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Segitiga
        val etAlas = findViewById<EditText>(R.id.etAlas)
        val etTinggi = findViewById<EditText>(R.id.etTinggi)
        val btnSegitiga = findViewById<Button>(R.id.btnHitungSegitiga)
        val tvHasilSegitiga = findViewById<TextView>(R.id.tvHasilSegitiga)

        btnSegitiga.setOnClickListener {
            val alas = etAlas.text.toString().toDoubleOrNull()
            val tinggi = etTinggi.text.toString().toDoubleOrNull()

            if (alas != null && tinggi != null) {
                val luas = 0.5 * alas * tinggi
                tvHasilSegitiga.text = "Hasil: $luas"
            } else {
                tvHasilSegitiga.text = "Input tidak valid!"
            }
        }

        // Kubus
        val etSisi = findViewById<EditText>(R.id.etSisi)
        val btnKubus = findViewById<Button>(R.id.btnHitungKubus)
        val tvHasilKubus = findViewById<TextView>(R.id.tvHasilKubus)

        btnKubus.setOnClickListener {
            val sisi = etSisi.text.toString().toDoubleOrNull()

            if (sisi != null) {
                val volume = sisi * sisi * sisi
                tvHasilKubus.text = "Hasil: $volume"
            } else {
                tvHasilKubus.text = "Input tidak valid!"
            }
        }
    }
}