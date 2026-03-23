package com.example.week10;

public class Movie {
    private String title;
    private Integer year;
    private String genre;
    private String posterResource;

    public Movie(String title, Integer year, String genre, String posterResource) {
        setTitle(title);
        setYear(year);
        setGenre(genre);
        setPosterResource(posterResource);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            this.title = "Unknown Title";
        } else {
            this.title = title;
        }
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        if (year == null || year <= 0) {
            this.year = null;
        } else {
            this.year = year;
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            this.genre = "Unknown Genre";
        } else {
            this.genre = genre;
        }
    }

    public String getPosterResource() {
        return posterResource;
    }

    public void setPosterResource(String posterResource) {
        if (posterResource == null || posterResource.trim().isEmpty()) {
            this.posterResource = "placeholder";
        } else {
            this.posterResource = posterResource;
        }
    }
}