package com.neecs.apiapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CharactersAdapter(private var dataList: List<Character>) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterImage: ImageView = itemView.findViewById(R.id.characterImage)
        val characterName: TextView = itemView.findViewById(R.id.characterName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = dataList[position]
        holder.characterName.text = character.name
        Glide.with(holder.itemView.context)
            .load(character.imageUrl)
            .into(holder.characterImage)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("characterName", character.name)
                putExtra("characterImageUrl", character.imageUrl)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataList.size

    fun updateData(newData: List<Character>) {
        dataList = newData
        notifyDataSetChanged()
    }
}