package org.example;

import java.util.ArrayList;

class NetflixService {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<TVShow> shows = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    public User user = null;

    public ArrayList<User> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    public ArrayList<Movie> getShows() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (TVShow show : shows){
            movies.add(show);
        }
        return movies;
    }
    public void setShows(ArrayList<TVShow> shows) {
        this.shows = shows;
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
    public User getUser() {
        return user;
    }

    public void addTVShow(TVShow tvShow){
        if (!doesShowExist(tvShow)){
            shows.add(tvShow);
            return;
        }
    }
    public boolean doesShowExist(TVShow tvShow){
        for (TVShow show : shows){
            if (show.equals(tvShow)){
                return true;
            }
        }
        return false;
    }

    public void addMovie(Movie movie){
        if (!doesMovieExist(movie)){
            movies.add(movie);
            return;
        }
    }
    public boolean doesMovieExist(Movie movie){
        if (movies.contains(movie)){
            return true;
        }
        return false;
    }

    public void createAccount(String username, String password) {
        User newuser = new User(username, password);
        users.add(newuser);
    }

    public boolean login(String username, String password) {
        User newUser = new User(username, password);
        for (User user : users){
            if (user.equals(newUser)){
                this.user = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        this.user = null;
    }

    public ArrayList<Movie> searchByTitle(String title) {
        ArrayList<Movie> found = new ArrayList<>();
        for (Movie movie : movies){
            if (movie.getTitle().equals(title)){
                found.add(movie);
            }
        }
        for (TVShow show : shows){
            if (show.getTitle().equals(title)){
                found.add(show);
            }
        }
        if (found.isEmpty()){
            return null;
        }
        else {
            return found;
        }
    }

    public ArrayList<Movie> searchByGenre(String genre) {
        ArrayList<Movie> found = new ArrayList<>();
        for (Movie movie : movies){
            if (movie.getGenre().equals(genre)){
                found.add(movie);
            }
        }
        for (TVShow show : shows){
            if (show.getGenre().equals(genre)){
                found.add(show);
            }
        }
        if (found.isEmpty()){
            return null;
        }
        else {
            return found;
        }
    }

    public ArrayList<Movie> searchByReleaseYear(int year) {
        ArrayList<Movie> found = new ArrayList<>();
        for (Movie movie : movies){
            if (movie.getReleaseYear() == (year)){
                found.add(movie);
            }
        }
        for (TVShow show : shows){
            if (show.getReleaseYear() == year){
                found.add(show);
            }
        }
        if (found.isEmpty()){
            return null;
        }
        else {
            return found;
        }
    }
    public boolean doesUsernameExist(String username){
        for (User user : users) {
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

}

