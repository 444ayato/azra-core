package com.example.azra_core.pertemuan_4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.azra_core.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Ambil data yang diteruskan
        val title = intent.getStringExtra("EXTRA_TITLE")
        val desc = intent.getStringExtra("EXTRA_SUBTITLE")

        binding.tvTitle.text = title
        binding.tvDescription.text = desc

        binding.tvPrev.setOnClickListener {
            finish()
        }
    }
}
