package com.example.azra_core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        val etUsername = findViewById<EditText>(R.id.etUsernameLogin)
        val etPassword = findViewById<EditText>(R.id.etPasswordLogin)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegisterGmail = findViewById<Button>(R.id.btnRegisterGmail)

        btnLogin.setOnClickListener {
            val inputUser = etUsername.text.toString().trim()
            val inputPass = etPassword.text.toString().trim()

            // 1. Ambil Data dari SharedPreferences (Data yang didaftarkan)
            val sharedPref = getSharedPreferences("BinaDesaData", Context.MODE_PRIVATE)
            val savedUser = sharedPref.getString("userLogin", "")
            val savedPass = sharedPref.getString("userPass", "")

            // 2. Logika Validasi Login (Dua Kondisi)
            if (inputUser.isEmpty() || inputPass.isEmpty()) {
                showErrorDialog("Login Gagal", "Username dan Password tidak boleh kosong!")
            }
            // KONDISI 1: Username == Password (Tugas Praktikum)
            // KONDISI 2: Username & Password cocok dengan data SharedPreferences (Tugas Registrasi)
            else if ((inputUser == inputPass) || (inputUser == savedUser && inputPass == savedPass)) {

                // Set Status Login Berhasil
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.apply()

                // Pindah ke MainActivity (Home)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                // Jika salah semua
                showErrorDialog("Akses Ditolak", "Username atau Password salah. Silakan coba lagi atau daftar akun baru.")
            }
        }

        // Tombol Register with Gmail
        btnRegisterGmail.setOnClickListener {
            val intent = Intent(this, InputEmailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showErrorDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("COBA LAGI") { dialog, _ -> dialog.dismiss() }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}