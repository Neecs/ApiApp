package com.neecs.apiapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.neecs.apiapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterName = intent.getStringExtra("characterName")
        val characterImageUrl = intent.getStringExtra("characterImageUrl")

        binding.characterNameTextView.text = characterName
        Glide.with(this)
            .load(characterImageUrl)
            .into(binding.characterImageView)
    }
}