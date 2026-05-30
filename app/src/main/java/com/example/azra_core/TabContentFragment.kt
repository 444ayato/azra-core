package com.example.azra_core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TabContentFragment : Fragment(R.layout.fragment_tab_content) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kategori = arguments?.getString("KATEGORI") ?: "Program Aktif"
        val rvBinaDesa = view.findViewById<RecyclerView>(R.id.rvBinaDesa)

        val dataBinaDesa = if (kategori == "Program Aktif") {
            listOf(
                Triple("Edukasi Sanitasi Sehat", "Desa Sukamaju", "https://images.unsplash.com/photo-1509099836639-18ba1795216d?w=500"),
                Triple("Digitalisasi UMKM Desa", "Desa Makmur", "https://images.unsplash.com/photo-1460925895917-afdab827c52f?w=500"),
                Triple("Penyuluhan Pertanian Hidroponik", "Desa Melati", "https://images.unsplash.com/photo-1530595467537-0b5996c41f2d?w=500")
            )
        } else {
            listOf(
                Triple("Pembangunan Jembatan Gantung", "Desa Waringin", "https://images.unsplash.com/photo-1541888946425-d81bb19240f5?w=500"),
                Triple("Pelatihan Jurnalistik Desa", "Desa Pasir Putih", "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=500")
            )
        }

        rvBinaDesa.layoutManager = LinearLayoutManager(requireContext())
        rvBinaDesa.adapter = BinaDesaAdapter(dataBinaDesa)
    }
}