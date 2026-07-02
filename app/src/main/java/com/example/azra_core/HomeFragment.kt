package com.example.azra_core

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.azra_core.utils.PermissionHelper
import com.google.android.material.tabs.TabLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val labelTab = arrayOf("Program Aktif", "Rencana Kerja")
    private lateinit var rvBerita: RecyclerView

    // Registrasi Launcher Izin Notifikasi (Sesuai Pertemuan 14)
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(context, "Izin Notifikasi Diaktifkan", Toast.LENGTH_SHORT).show()
                bukaHalamanPengajuan()
            } else {
                Toast.makeText(context, "Aplikasi butuh izin notifikasi untuk fitur reminder!", Toast.LENGTH_LONG).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayoutHome)
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPagerHome)
        val btnLogout = view.findViewById<Button>(R.id.btnLogoutHome)
        val btnAjukanProyek = view.findViewById<Button>(R.id.btnAjukanProyekMenu)
        rvBerita = view.findViewById(R.id.rvBeritaHome)

        rvBerita.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val adapter = HomeTabAdapter(this, labelTab)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = labelTab[position]
        }.attach()

        loadBeritaDariAPI()

        // Pemicu Buka Form dengan Cek Izin Dulu
        btnAjukanProyek.setOnClickListener {
            if (PermissionHelper.isNotificationPermissionRequired()) {
                val permission = Manifest.permission.POST_NOTIFICATIONS
                if (!PermissionHelper.hasPermission(requireContext(), permission)) {
                    PermissionHelper.requestPermission(notificationPermissionLauncher, permission)
                } else {
                    bukaHalamanPengajuan()
                }
            } else {
                bukaHalamanPengajuan()
            }
        }

        btnLogout.setOnClickListener {
            val sharedPref = requireActivity().getSharedPreferences("BinaDesaData", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("isLogin", false).apply()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun bukaHalamanPengajuan() {
        startActivity(Intent(requireContext(), PengajuanProyekActivity::class.java))
    }

    private fun loadBeritaDariAPI() {
        viewLifecycleOwner.lifecycleScope.launch {
            val listHasil = withContext(Dispatchers.IO) {
                val dataAman = mutableListOf<Pair<String, String>>()
                try {
                    val url = URL("https://api.spaceflightnewsapi.net/v4/articles/?limit=5")
                    val koneksi = url.openConnection() as HttpURLConnection
                    koneksi.requestMethod = "GET"
                    koneksi.connectTimeout = 8000

                    if (koneksi.responseCode == HttpURLConnection.HTTP_OK) {
                        val reader = BufferedReader(InputStreamReader(koneksi.inputStream))
                        val response = StringBuilder()
                        var line: String?
                        while (reader.readLine().also { line = it } != null) {
                            response.append(line)
                        }
                        reader.close()

                        val jsonObject = JSONObject(response.toString())
                        val jsonArray = jsonObject.getJSONArray("results")
                        for (i in 0 until jsonArray.length()) {
                            val item = jsonArray.getJSONObject(i)
                            val judul = item.getString("title")
                            val gambar = item.getString("image_url")
                            dataAman.add(Pair(judul, gambar))
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                dataAman
            }

            if (listHasil.isNotEmpty()) {
                rvBerita.adapter = BeritaAdapter(listHasil)
            } else {
                val dummy = listOf(
                    Pair("Digitalisasi Sektor UMKM Desa Guna Mempercepat Roda Ekonomi Wilayah", "https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=500"),
                    Pair("Edukasi Pentingnya Sanitasi Sehat Lingkungan Desa Mandiri Binaan", "https://images.unsplash.com/photo-1509099836639-18ba1795216d?w=500")
                )
                rvBerita.adapter = BeritaAdapter(dummy)
            }
        }
    }
}