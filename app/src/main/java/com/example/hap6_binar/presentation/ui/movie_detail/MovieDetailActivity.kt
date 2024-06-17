package com.example.hap6_binar.presentation.ui.movie_detail

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hap6_binar.R
import com.example.hap6_binar.domain.model.Movie
import com.google.android.material.textview.MaterialTextView

@Suppress("DEPRECATION")
class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getParcelableExtra<Movie>("movie")
        val titleTextView: MaterialTextView = findViewById(R.id.titleTextView)
        val posterImageView: ImageView = this.findViewById(R.id.posterImageView)
        val descriptionTextView: MaterialTextView = findViewById(R.id.descriptionTextView)

        movie?.let {
            titleTextView.text = it.title
            descriptionTextView.text = it.description
            Glide.with(this)
                .load(it.posterUrl)
                .into(posterImageView)
        }
    }
}
