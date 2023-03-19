package org.example;

import java.util.ArrayList;
import java.util.Objects;

class User {
    private String username;
    private String password;
    private ArrayList<Movie> favorites = new ArrayList<>();
    private ArrayList<Movie> watchHistory = new ArrayList<>();
    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void addToWatched(Movie movie){
        if (!watchHistory.contains(movie)){
            watchHistory.add(movie);
        }
    }
    public ArrayList<Movie> searchByTitle(String title) {
        ArrayList<Movie> found = new ArrayList<>();
        for (Movie movie : favorites){
            if (movie.getTitle().equals(title)){
                found.add(movie);
            }
        }
        if (!found.isEmpty()) {
            return found;
        }
        else {
            return null;
        }
    }
    public ArrayList<Movie> searchByGenre(String genre) {
        ArrayList<Movie> found = new ArrayList<>();
        for (Movie movie : favorites){
            if (movie.getGenre().equals(genre)){
                found.add(movie);
            }
        }
        if (found.size() != 0) {
            return found;
        }
        else {
            return null;
        }
    }
    public ArrayList<Movie> searchByReleaseYear(int year) {
        ArrayList<Movie> found = new ArrayList<>();
        for (Movie movie : favorites){
            if (movie.getReleaseYear() == year){
                found.add(movie);
            }
        }
        if (found.size() != 0) {
            return found;
        }
        else {
            return null;
        }
    }
    public void addToFavorites(Movie movie) {
        if(!favorites.contains(movie)) {
            favorites.add(movie);
            System.out.println("Movie added to favorites successfully");
            return;
        }
        System.out.println("Movie was already in the favorite list!");
    }
    public void viewFavorites() {
        if (favorites.isEmpty()){
            System.out.println("No movie/TV show in your favorite list!");
        }
        else {
            for (Movie movie : favorites) {
                System.out.println(movie);
            }
        }
    }
    public ArrayList<Movie> getRecommendations(ArrayList<Movie> allMovies) {
        ArrayList<Movie> recoms = new ArrayList<>();
        for (Movie movie : allMovies){                  //add movies with the same genre or mutual actors
            for (Movie faveMovie : favorites){          // of the user's favorite list of movies.
                if ((hasSameActor(movie.getCast(), faveMovie.getCast()) ||
                        movie.getGenre().equals(faveMovie.getGenre())) && !(movie.equals(faveMovie))){
                    recoms.add(movie);
                }
            }
        }
        return recoms;
    }
    public boolean hasSameActor(ArrayList<String> cast1, ArrayList<String> cast2){
        for (String actor1 : cast1){
            for (String actor2 : cast2){
                if (actor1.equals(actor2)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", favorites=" + favorites +
                ", watchHistory=" + watchHistory +
                '}';
    }
}
