package com.example.azra_core.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Pengingat Lokasi Proyek"
        val message = intent.getStringExtra("message") ?: "Waktunya mengecek status pengajuan"
        val targetClassName = intent.getStringExtra("target_activity")
        val extraKey = intent.getStringExtra("extra_key")
        val extraValue = intent.getStringExtra("extra_value")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            val clazz = Class.forName(targetClassName)
            Intent(context, clazz).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                if (!extraKey.isNullOrEmpty()) {
                    putExtra(extraKey, extraValue)
                }
            }
        } else {
            context.packageManager.getLaunchIntentForPackage(context.packageName)
        }

        if (targetIntent != null) {
            NotificationHelper.showNotification(
                context = context,
                title = title,
                message = message,
                intent = targetIntent
            )
        }
    }
}