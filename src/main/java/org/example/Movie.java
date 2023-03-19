package org.example;

import java.util.ArrayList;
import java.util.Objects;

class Movie {
    private String title;
    private String genre;
    private int releaseYear;
    private int duration;
    private double rating;
    private ArrayList<String> cast = new ArrayList<>();

    public Movie(){}
    public Movie(String title, String genre, int releaseYear, int duration, double rating, ArrayList<String> cast) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.rating = rating;
        this.cast = cast;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public ArrayList<String> getCast() {
        return cast;
    }
    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }
    public void addToCast(String actor){
        this.cast.add(actor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return releaseYear == movie.releaseYear && duration == movie.duration && Double.compare(movie.rating, rating) == 0 && Objects.equals(title, movie.title) && Objects.equals(genre, movie.genre) && Objects.equals(cast, movie.cast);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Title='" + title + '\'' +
                ", Genre='" + genre + '\'' +
                ", Release Year=" + releaseYear +
                ", Duration=" + duration +
                ", Rating=" + rating +
                ", Cast=" + cast +
                '}';
    }
}
