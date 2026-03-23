package com.example.week10;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * RecyclerView adapter for displaying movie items.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private final List<Movie> movieList;

    /**
     * Creates a new adapter with the given movie list.
     *
     * @param movieList the list of movies to display
     */
    public MovieAdapter(@NonNull List<Movie> movieList) {
        this.movieList = movieList;
    }

    /**
     * Creates a new ViewHolder for a movie item.
     *
     * @param parent the parent ViewGroup
     * @param viewType the type of view
     * @return a new MovieViewHolder
     */
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false)
        );
    }

    /**
     * Binds movie data to the ViewHolder at the given position.
     *
     * @param holder the ViewHolder to bind data to
     * @param position the position of the item in the list
     */
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movieList.get(position));
    }

    /**
     * Returns the total number of movie items.
     *
     * @return the item count
     */
    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
