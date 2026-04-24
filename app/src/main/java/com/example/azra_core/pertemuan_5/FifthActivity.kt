package com.example.azra_core.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.azra_core.R
import com.example.azra_core.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Setup UI Components
        setupToolbar()
        setupActionButtons()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Fifth"
            subtitle = "Modern Interface"
            setDisplayHomeAsUpEnabled(true)

            // Perbaikan Error: Menggunakan icon standar agar tidak merah (Unresolved Reference)
            setHomeAsUpIndicator(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        }
    }

    private fun setupActionButtons() {
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    // 3. Membuat Option Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // 4. Menangani klik pada Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Menutup activity dan kembali ke sebelumnya
                finish()
                true
            }
            R.id.action_search -> {
                showToast("Fitur Cari dipilih")
                true
            }
            R.id.action_settings -> {
                showToast("Membuka Pengaturan")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}