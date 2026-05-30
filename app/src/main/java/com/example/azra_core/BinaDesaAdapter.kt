package com.example.azra_core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BinaDesaAdapter(private val listData: List<Triple<String, String, String>>) :
    RecyclerView.Adapter<BinaDesaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNamaProgram)
        val tvLokasi: TextView = view.findViewById(R.id.tvLokasiDesa)
        val ivFoto: ImageView = view.findViewById(R.id.ivFotoProgram)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bina_desa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (program, lokasi, urlGambar) = listData[position]
        holder.tvNama.text = program
        holder.tvLokasi.text = lokasi

        Glide.with(holder.itemView.context)
            .load(urlGambar)
            .into(holder.ivFoto)
    }

    override fun getItemCount(): Int = listData.size
}