package com.example.week10;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Represents a movie item loaded from the JSON file.
 * Each movie contains a title, year, genre, and poster resource name.
 */
public class Movie {

    private final String title;
    private final Integer year;
    private final String genre;
    private final String posterResource;

    /**
     * Creates a new Movie object.
     *
     * @param title the title of the movie
     * @param year the release year of the movie, may be null if invalid or missing
     * @param genre the genre of the movie
     * @param posterResource the drawable resource name of the movie poster
     */
    public Movie(@Nullable String title, @Nullable Integer year, @Nullable String genre, @Nullable String posterResource) {
        this.title = (title == null || title.trim().isEmpty()) ? "Unknown Title" : title;
        this.year = year;
        this.genre = (genre == null || genre.trim().isEmpty()) ? "Unknown Genre" : genre;
        this.posterResource = (posterResource == null || posterResource.trim().isEmpty()) ? "placeholder" : posterResource;
    }

    /**
     * Returns the movie title.
     *
     * @return the title of the movie
     */
    @NonNull
    public String getTitle() {
        return title;
    }

    /**
     * Returns the release year of the movie.
     *
     * @return the release year, or null if missing/invalid
     */
    @Nullable
    public Integer getYear() {
        return year;
    }

    /**
     * Returns the genre of the movie.
     *
     * @return the genre of the movie
     */
    @NonNull
    public String getGenre() {
        return genre;
    }

    /**
     * Returns the drawable resource name of the poster.
     *
     * @return the poster resource name
     */
    @NonNull
    public String getPosterResource() {
        return posterResource;
    }
}
