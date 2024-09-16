package com.neecs.apiapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val characterName = intent.getStringExtra("characterName")
        val characterImageUrl = intent.getStringExtra("characterImageUrl")

        val characterImageView: ImageView = findViewById(R.id.characterImageView)
        val characterNameTextView: TextView = findViewById(R.id.characterNameTextView)

        characterNameTextView.text = characterName
        Glide.with(this)
            .load(characterImageUrl)
            .into(characterImageView)
    }
}