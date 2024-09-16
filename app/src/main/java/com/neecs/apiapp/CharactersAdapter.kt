package com.neecs.apiapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neecs.apiapp.databinding.CharacterItemBinding

class CharactersAdapter(private var dataList: List<Character>) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    class ViewHolder(val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = dataList[position]
        holder.binding.characterName.text = character.name
        Glide.with(holder.itemView.context)
            .load(character.imageUrl)
            .into(holder.binding.characterImage)

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