package com.example.azra_core

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.azra_core.utils.NotificationHelper
import com.example.azra_core.utils.ReminderHelper

class PengajuanProyekActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengajuan_proyek)

        val etNamaProyek = findViewById<EditText>(R.id.etNamaProyek)
        val etWaktuReminder = findViewById<EditText>(R.id.etWaktuReminder)
        val btnKirim = findViewById<Button>(R.id.btnKirimPengajuan)

        btnKirim.setOnClickListener {
            val namaProyek = etNamaProyek.text.toString().trim()
            val menitText = etWaktuReminder.text.toString().trim()

            if (namaProyek.isEmpty() || menitText.isEmpty()) {
                Toast.makeText(this, "Mohon lengkapi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val menit = menitText.toInt()

            // 1. Picu Local Notification Instan (Pengajuan Berhasil)
            val intentInstan = Intent(this, MainActivity::class.java)
            NotificationHelper.showNotification(
                context = this,
                title = "Pengajuan Berhasil",
                message = "Pengajuan untuk lokasi '$namaProyek' telah dikirim dan sedang dalam peninjauan.",
                intent = intentInstan
            )

            // 2. Set alarm reminder berwaktu sesuai input menit user
            ReminderHelper.setReminder(
                context = this,
                minutesFromNow = menit,
                title = "Status Proyek: DISETUJUI ✔",
                message = "Selamat! Lokasi proyek '$namaProyek' telah disetujui oleh tim peninjau Desa.",
                targetActivity = DetailProyekDisetujuiActivity::class.java,
                dataKey = "EXTRA_NAMA_PROYEK",
                dataValue = namaProyek
            )

            Toast.makeText(this, "Pengajuan disimpan. Reminder aktif untuk $menit menit lagi!", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}