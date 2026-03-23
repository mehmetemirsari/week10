package com.example.week10;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Main activity that displays a list of movies using a RecyclerView.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMovies;

    /**
     * Called when the activity is created.
     * Initializes the RecyclerView and loads movie data from JSON.
     *
     * @param savedInstanceState the saved instance state bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        loadMovieData();
    }

    /**
     * Loads movie data from the JSON file and displays it in the RecyclerView.
     * Shows a Toast message if no valid data is found or if an error occurs.
     */
    private void loadMovieData() {
        try {
            List<Movie> movieList = JsonUtils.loadMoviesFromJson(this);

            if (movieList.isEmpty()) {
                Toast.makeText(this, "No valid movie data found.", Toast.LENGTH_LONG).show();
            } else {
                MovieAdapter adapter = new MovieAdapter(movieList);
                recyclerViewMovies.setAdapter(adapter);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error loading movie data.", Toast.LENGTH_LONG).show();
        }
    }
}
