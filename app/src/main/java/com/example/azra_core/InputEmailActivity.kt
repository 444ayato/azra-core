package com.example.azra_core

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class InputEmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_email)

        val etEmail = findViewById<TextInputEditText>(R.id.etEmailInput)
        val btnContinue = findViewById<Button>(R.id.btnContinue)

        btnContinue.setOnClickListener {
            val email = etEmail.text.toString().trim()

            // Validasi 1: Tidak boleh kosong
            if (email.isEmpty()) {
                showErrorDialog("Email tidak boleh kosong!")
                return@setOnClickListener
            }

            // Validasi 2: Harus domain @gmail.com
            if (!email.endsWith("@gmail.com")) {
                showErrorDialog("Format salah! Harus menggunakan domain @gmail.com")
                return@setOnClickListener
            }

            // Jika valid: Pindah ke RegisterActivity sambil bawa data email
            val intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("EXTRA_EMAIL", email)
            startActivity(intent)
        }
    }

    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Validasi Gagal")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}