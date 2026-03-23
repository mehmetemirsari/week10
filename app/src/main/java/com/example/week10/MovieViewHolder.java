package com.example.week10;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder for displaying a single movie item in the RecyclerView.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {

    private final ImageView imageViewPoster;
    private final TextView textViewTitle;
    private final TextView textViewYear;
    private final TextView textViewGenre;

    /**
     * Creates a new MovieViewHolder and initializes its views.
     *
     * @param itemView the item view for this holder
     */
    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewYear = itemView.findViewById(R.id.textViewYear);
        textViewGenre = itemView.findViewById(R.id.textViewGenre);
    }

    /**
     * Binds a Movie object to the views in this ViewHolder.
     *
     * @param movie the movie to display
     */
    public void bind(@NonNull Movie movie) {
        textViewTitle.setText(movie.getTitle());
        textViewGenre.setText(movie.getGenre());

        if (movie.getYear() != null) {
            textViewYear.setText(String.valueOf(movie.getYear()));
        } else {
            textViewYear.setText("Unknown Year");
        }

        int resId = itemView.getContext().getResources().getIdentifier(
                movie.getPosterResource(),
                "drawable",
                itemView.getContext().getPackageName()
        );

        if (resId != 0) {
            imageViewPoster.setImageResource(resId);
        } else {
            imageViewPoster.setImageResource(R.drawable.placeholder);
        }
    }
}
