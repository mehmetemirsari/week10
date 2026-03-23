package com.example.week10;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading and parsing movie data from a JSON file.
 */
public class JsonUtils {

    private static final String TAG = "JsonUtils";

    /**
     * Private constructor to prevent instantiation.
     */
    private JsonUtils() {
    }

    /**
     * Loads movie data from the assets folder and converts it into a list of Movie objects.
     *
     * @param context the application context used to access assets
     * @return a list of movies, or an empty list if an error occurs
     */
    @NonNull
    public static List<Movie> loadMoviesFromJson(@NonNull Context context) {
        List<Movie> movieList = new ArrayList<>();

        try {
            InputStream inputStream = context.getAssets().open("movies.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();

            JSONArray jsonArray = new JSONArray(builder.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject object = jsonArray.getJSONObject(i);

                    String title = object.optString("title", "Unknown Title");
                    String genre = object.optString("genre", "Unknown Genre");
                    String poster = object.optString("poster", "placeholder");

                    Integer year = null;
                    Object yearObject = object.opt("year");

                    if (yearObject instanceof Integer) {
                        year = (Integer) yearObject;
                    } else if (yearObject instanceof String) {
                        try {
                            year = Integer.parseInt((String) yearObject);
                        } catch (NumberFormatException e) {
                            Log.e(TAG, "Invalid year format for movie: " + title);
                        }
                    }

                    movieList.add(new Movie(title, year, genre, poster));

                } catch (Exception e) {
                    handleJsonException("Error parsing movie object", e);
                }
            }

        } catch (Exception e) {
            handleJsonException("Error loading JSON file", e);
        }

        return movieList;
    }

    /**
     * Handles exceptions that occur during JSON reading or parsing.
     *
     * @param message a custom message describing the error
     * @param e the exception that occurred
     */
    private static void handleJsonException(@NonNull String message, @NonNull Exception e) {
        Log.e(TAG, message, e);
    }
}
