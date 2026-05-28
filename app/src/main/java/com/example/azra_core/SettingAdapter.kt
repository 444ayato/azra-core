package com.example.azra_core

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class SettingAdapter(context: Context, private val items: List<SettingItem>) :
    ArrayAdapter<SettingItem>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_setting, parent, false)
        val item = items[position]

        val icon = view.findViewById<ImageView>(R.id.imgSettingIcon)
        val title = view.findViewById<TextView>(R.id.txtSettingTitle)
        val desc = view.findViewById<TextView>(R.id.txtSettingDesc)

        icon.setImageResource(item.iconRes)
        title.text = item.title
        desc.text = item.desc

        return view
    }
}