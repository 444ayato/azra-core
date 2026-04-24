package com.example.azra_core.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.azra_core.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Ambil data dari WelcomeActivity
        val title = intent.getStringExtra("EXTRA_TITLE")
        val desc = intent.getStringExtra("EXTRA_SUBTITLE")

        // Tampilkan ke UI (Pastikan ID tvTitle & tvDescription ada di XML)
        binding.tvTitle.text = title
        binding.tvDescription.text = desc

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            // Teruskan data ke halaman berikutnya
            intent.putExtra("EXTRA_TITLE", title)
            intent.putExtra("EXTRA_SUBTITLE", desc)
            startActivity(intent)
        }

        binding.tvSkip.setOnClickListener {
            finish()
        }
    }
}