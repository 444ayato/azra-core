package com.example.azra_core.pertemuan_3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.azra_core.databinding.ActivityWelcomeBinding
import com.example.azra_core.pertemuan_4.OrderActivity
import com.example.azra_core.pertemuan_4.ProductActivity
import com.example.azra_core.MainActivity
import com.example.azra_core.pertemuan_3.LoginActivity
import com.google.android.material.snackbar.Snackbar

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val titleData = binding.tvTitle.text.toString()
        val subtitleData = binding.tvSubtitle.text.toString()

        // Navigasi ke Hitung Bangun Ruang
        binding.btnHitung.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("EXTRA_TITLE", titleData)
            intent.putExtra("EXTRA_SUBTITLE", subtitleData)
            startActivity(intent)
        }

        // Navigasi ke Product
        binding.btnProducts.setOnClickListener {
            val intent = Intent(this, ProductActivity::class.java)
            intent.putExtra("EXTRA_TITLE", titleData)
            intent.putExtra("EXTRA_SUBTITLE", subtitleData)
            startActivity(intent)
        }

        // Navigasi ke Order
        binding.btnOrder.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("EXTRA_TITLE", titleData)
            intent.putExtra("EXTRA_SUBTITLE", subtitleData)
            startActivity(intent)
        }

        // Logout logic
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Konfirmasi Logout")
                setMessage("Apakah Anda yakin ingin keluar?")
                setPositiveButton("Iya") { _, _ ->
                    val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                create().show()
            }
        }
    }
}