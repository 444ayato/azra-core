package com.example.azra_core

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etFullName = findViewById<TextInputEditText>(R.id.etFullName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmailRegister)
        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etPasswordRegister)
        val btnSubmit = findViewById<Button>(R.id.btnSubmitRegister)

        // Menerima Email dari halaman InputEmailActivity
        val emailPassed = intent.getStringExtra("KEY_EMAIL_PASSED")
        if (emailPassed != null) {
            etEmail.setText(emailPassed)
        }

        btnSubmit.setOnClickListener {
            val name = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // VALIDASI
            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                showAlert("Form Belum Lengkap", "Harap isi semua kolom data diri Anda.")
                return@setOnClickListener
            }

            if (username.contains(" ")) {
                showAlert("Username Tidak Valid", "Username tidak boleh menggunakan spasi.")
                return@setOnClickListener
            }

            if (password.length < 6) {
                showAlert("Password Terlalu Pendek", "Gunakan minimal 6 karakter untuk keamanan.")
                return@setOnClickListener
            }

            // SIMPAN DATA KE SHAREDPREFERENCES
            val sharedPref = getSharedPreferences("BinaDesaData", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("userName", name)
            editor.putString("userEmail", email)
            editor.putString("userLogin", username)
            editor.putString("userPass", password)
            editor.apply()

            // DIALOG BERHASIL
            MaterialAlertDialogBuilder(this)
                .setTitle("Registrasi Berhasil")
                .setMessage("Akun Anda telah aktif. Silakan login menggunakan username dan password Anda.")
                .setCancelable(false)
                .setPositiveButton("MENUJU LOGIN") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .show()
        }
    }

    private fun showAlert(title: String, msg: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton("OKE") { d, _ -> d.dismiss() }
            .show()
    }
}