package com.example.azra_core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BeritaAdapter(private val listBerita: List<Pair<String, String>>) :
    RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.tvJudulBerita)
        val image: ImageView = v.findViewById(R.id.ivBerita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (judul, urlGambar) = listBerita[position]
        holder.title.text = judul
        Glide.with(holder.itemView.context).load(urlGambar).into(holder.image)
    }

    override fun getItemCount(): Int = listBerita.size
}