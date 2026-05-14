package com.example.azra_core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnWeb = view.findViewById<Button>(R.id.btnWebView)
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        btnWeb.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        btnLogout.setOnClickListener {
            val sharedPref = requireActivity().getSharedPreferences("TugasBinaDesa", Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()
            Toast.makeText(requireContext(), "Logout Berhasil", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }
    }
}