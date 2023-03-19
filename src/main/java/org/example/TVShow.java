package org.example;

import java.util.ArrayList;
import java.util.Objects;

class TVShow extends Movie{
    private int episodes;

    public TVShow(){}

    public TVShow(String title, String genre, int releaseYear, int duration, double rating, ArrayList<String> cast, int episodes) {
        super(title, genre, releaseYear, duration, rating, cast);
        this.episodes = episodes;
    }

    public int getEpisodes() {
        return episodes;
    }
    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }
    public void addEpisode(int num){
        this.episodes++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TVShow tvShow = (TVShow) o;
        return episodes == tvShow.episodes;
    }

    @Override
    public String toString() {
        return "TVShow{" +
                "Title='" + this.getTitle() + '\'' +
                ", Genre='" + this.getGenre() + '\'' +
                ", Release Year=" + this.getReleaseYear() +
                ", Duration=" + this.getDuration() +
                ", Rating=" + this.getRating() +
                ", Cast=" + this.getCast() +
                ", Number of Episodes=" + episodes +
                '}';
    }
}
