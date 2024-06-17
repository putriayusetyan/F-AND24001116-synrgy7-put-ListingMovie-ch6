package com.example.hap6_binar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hap6_binar.ui.theme.Hap6binarTheme
import com.example.hap6_binar.R

class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val movieAdapter = MovieAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        movieViewModel.movies.observe(this, Observer { movies ->
            movieAdapter.submitList(movies)
        })

        movieViewModel.fetchPopularMovies("9a33a614e8bfb6d173db7fbc3d402542", 1)
    }
}
