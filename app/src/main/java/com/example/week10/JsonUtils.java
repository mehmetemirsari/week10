package com.example.week10;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<Movie> loadMoviesFromJson(Context context) {
        List<Movie> movies = new ArrayList<>();

        try {
            String jsonString = readJsonFile(context, "movies.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject object = jsonArray.getJSONObject(i);

                    String title = null;
                    Integer year = null;
                    String genre = null;
                    String poster = null;

                    if (object.has("title") && !object.isNull("title")) {
                        title = object.getString("title");
                    }

                    if (object.has("year") && !object.isNull("year")) {
                        Object yearObject = object.get("year");

                        if (yearObject instanceof Integer) {
                            int parsedYear = (Integer) yearObject;
                            if (parsedYear > 0) {
                                year = parsedYear;
                            }
                        } else if (yearObject instanceof String) {
                            try {
                                int parsedYear = Integer.parseInt((String) yearObject);
                                if (parsedYear > 0) {
                                    year = parsedYear;
                                }
                            } catch (NumberFormatException e) {
                                Log.e("JsonUtils", "Invalid year string at index " + i);
                            }
                        } else {
                            Log.e("JsonUtils", "Unsupported year format at index " + i);
                        }
                    }

                    if (object.has("genre") && !object.isNull("genre")) {
                        genre = object.getString("genre");
                    }

                    if (object.has("poster") && !object.isNull("poster")) {
                        poster = object.getString("poster");
                    }

                    if (title == null && year == null && genre == null && poster == null) {
                        Log.e("JsonUtils", "Empty movie object at index " + i);
                        continue;
                    }

                    Movie movie = new Movie(title, year, genre, poster);
                    movies.add(movie);

                } catch (JSONException e) {
                    handleJsonException(e);
                }
            }

        } catch (IOException e) {
            handleJsonException(e);
        } catch (JSONException e) {
            handleJsonException(e);
        } catch (Exception e) {
            handleJsonException(e);
        }

        return movies;
    }

    private static String readJsonFile(Context context, String fileName) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStream inputStream = context.getAssets().open(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();
        inputStream.close();

        return builder.toString();
    }

    public static void handleJsonException(Exception exception) {
        Log.e("JsonUtils", "Error while loading JSON: " + exception.getMessage(), exception);
    }
}