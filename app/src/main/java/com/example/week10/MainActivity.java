package com.example.week10;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        movies = new ArrayList<>();

        setupRecyclerView();
        loadMovieData();
    }

    private void setupRecyclerView() {
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(movies);
        movieRecyclerView.setAdapter(adapter);
    }

    private void loadMovieData() {
        try {
            List<Movie> loadedMovies = JsonUtils.loadMoviesFromJson(this);

            if (loadedMovies == null || loadedMovies.isEmpty()) {
                showError("No valid movie data found.");
                return;
            }

            adapter.updateMovies(loadedMovies);

        } catch (Exception e) {
            showError("Error loading movie data.");
        }
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}