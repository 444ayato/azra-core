package com.example.azra_core

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lvSettings = view.findViewById<ListView>(R.id.lvSettings)

        // Data List Sederhana untuk Menu Settings (Sudah di-fix android double-nya)
        val listMenu = listOf(
            SettingItem("Account Privacy", "Kelola visibilitas data monitoring desa Anda", android.R.drawable.ic_lock_idle_lock),
            SettingItem("About Bina Desa", "Informasi lengkap mengenai software tech ini", android.R.drawable.ic_menu_info_details),
            SettingItem("Terms of Service", "Syarat dan ketentuan hak akses mitra aplikasi", android.R.drawable.ic_menu_agenda),
            SettingItem("Help Center", "Hubungi admin tech support jika terjadi kendala", android.R.drawable.ic_menu_call)
        )

        // Set Custom Adapter ke dalam ListView
        val adapter = SettingAdapter(requireContext(), listMenu)
        lvSettings.adapter = adapter

        // Klik Item Handler
        lvSettings.setOnItemClickListener { _, _, position, _ ->
            val clickedItem = listMenu[position]
            Toast.makeText(requireContext(), "Membuka ${clickedItem.title}", Toast.LENGTH_SHORT).show()
        }
    }
}