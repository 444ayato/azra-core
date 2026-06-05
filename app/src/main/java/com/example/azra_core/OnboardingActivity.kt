package com.example.azra_core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val viewPager = findViewById<ViewPager2>(R.id.viewPagerOnboarding)
        val btnStart = findViewById<Button>(R.id.btnStart)

        val dataList = listOf(
            Triple("Selamat Datang", "Platform pengelola program pemberdayaan wilayah pedesaan terpadu.", R.drawable.logo_proyek),
            Triple("Pantau Realisasi", "Lihat realisasi kerja nyata mahasiswa beserta progres pembangunan wilayah.", R.drawable.logo_proyek),
            Triple("Mulai Langkahmu", "Mari berkontribusi membangun esensi kemakmuran wilayah desa sekarang.", R.drawable.logo_proyek)
        )

        viewPager.adapter = OnboardingAdapter(dataList)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Tombol "Ayo Mulai" hanya muncul di halaman paling terakhir (index 2)
                if (position == dataList.size - 1) {
                    btnStart.visibility = View.VISIBLE
                } else {
                    btnStart.visibility = View.GONE
                }
            }
        })

        btnStart.setOnClickListener {
            val sharedPref = getSharedPreferences("BinaDesaData", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("hasSeenOnboarding", true).apply()

            // Diarahkan langsung ke halaman Login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    class OnboardingAdapter(private val data: List<Triple<String, String, Int>>) :
        RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val title: TextView = v.findViewById(R.id.tvTitleOnboarding)
            val desc: TextView = v.findViewById(R.id.tvDescOnboarding)
            val img: ImageView = v.findViewById(R.id.ivOnboarding)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_onboarding, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val (titleText, descText, imgRes) = data[position]
            holder.title.text = titleText
            holder.desc.text = descText
            holder.img.setImageResource(imgRes)
        }

        override fun getItemCount(): Int = data.size
    }
}