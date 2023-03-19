package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("               " + "\u001B[31m" + "\u001B[47m" + "NETFLIX" + "\u001B[0m");
        System.out.println("** Watch TV Shows and Movies Online **");
        NetflixService netflix = new NetflixService();
        Movie movie = new Movie("Atonement", "Drama", 2007, 123, 7.8, new ArrayList<>(Arrays.asList("Saoirse Ronan", "James McAvoy", "Keira Knightley")));
        netflix.addMovie(movie);
        movie = new Movie("The Shawshank Redemption", "Drama/Crime", 1994, 142, 9.3, new ArrayList<>(Arrays.asList("Morgan Freeman" , "Tim Robbins" , "Clancy Brown")));
        netflix.addMovie(movie);
        movie = new Movie("The Shape of Water", "Romance/Fantasy", 2017, 123, 7.3, new ArrayList<>(Arrays.asList("Sally Hawkins", "Doug Jones", "Octavia Spencer")));
        netflix.addMovie(movie);
        movie = new Movie("Phantom Thread", "Romance/Drama", 2017, 131, 7.4, new ArrayList<>(Arrays.asList("Daniel Day-Lewis", "Vicky Krieps", "Lesley Manville")));
        netflix.addMovie(movie);
        movie = new Movie("Darkest Hour", "War/Drama", 2017, 125, 7.4, new ArrayList<>(Arrays.asList("Gary Oldman", "Lily James", "Kristin Scott Thomas")));
        netflix.addMovie(movie);
        movie = new Movie("Blade Runner 2049", "Sci-fi/Action", 2017, 163, 8, new ArrayList<>(Arrays.asList("Ryan Gosling", "Harrison Ford", "Ana de Armas")));
        netflix.addMovie(movie);


        TVShow show = new TVShow("Peaky Blinders", "Drama", 2013, 63, 8.8,new ArrayList<>(Arrays.asList("Cillian Murphy", "Paul Anderson", "Tom Hardy")), 36);
        netflix.addTVShow(show);
        show = new TVShow("The Handmaid's Tale", "Sci-fi", 2017, 50, 8.4,new ArrayList<>(Arrays.asList("Elisabeth Moss", "Yvonne Strahovski", "Madeline Brewer")), 56);
        netflix.addTVShow(show);
        show = new TVShow("Killing Eve", "Thriller", 2018, 50, 8.8,new ArrayList<>(Arrays.asList("Jodie Comer", "Sandra Oh", "Fiona Shaw")), 43);
        netflix.addTVShow(show);
        show = new TVShow("The 100", "Sci-fi", 2014, 39, 7.6,new ArrayList<>(Arrays.asList("Eliza Taylor", "Bob Morley", "Marie Avgeropoulos")), 100);
        netflix.addTVShow(show);
        show = new TVShow("Black Mirror", "Sci-fi", 2011, 89, 8.8,new ArrayList<>(Arrays.asList("Bryce Dallas Howard", "Hayley Atwell", "Michaela Coel")), 100);
        netflix.addTVShow(show);
        show = new TVShow("Taboo", "Drama", 2017, 65, 8.3,new ArrayList<>(Arrays.asList("Tom Hardy", "Oona Chaplin", "Jessie Buckley")), 8);
        netflix.addTVShow(show);

        while (enter(netflix)){
            runMenu(netflix);
        }
    }
    public static void runMenu(NetflixService netflix){
        Scanner in = new Scanner(System.in);
        int op = 0;
        while (op != 7){
            System.out.println("MENU");
            System.out.println("1. Search");
            System.out.println("2. Watch");
            System.out.println("3. Add to Favorite");
            System.out.println("4. View Liked Movies & TV shows");
            System.out.println("5. Search in Favorites");
            System.out.println("6. Recommendations Just For You!");
            System.out.println("7. Log out");
            op = in.nextInt();
            switch (op){
                case 1:
                    printList(search(netflix));
                    break;
                case 2:
                    ArrayList<Movie> found = search(netflix);
                    if (found != null){
                        printList(found);
                        Movie movie;
                        System.out.println("Which one do you want to watch?");
                        movie = found.get(in.nextInt() - 1);
                        netflix.getUser().addToWatched(movie);
                    }
                    break;
                case 3:
                    found = search(netflix);
                    if (found != null){
                        printList(found);
                        Movie movie;
                        System.out.println("Which one do you want to add to favorites?");
                        movie = found.get(in.nextInt() - 1);
                        netflix.getUser().addToFavorites(movie);
                    }
                    break;
                case 4:
                    netflix.getUser().viewFavorites();
                    break;
                case 5:
                    found = searchInFavorites(netflix);
                    if (found != null){
                        printList(found);
                    }
                    break;
                case 6:
                    ArrayList<Movie> foundMovies = netflix.getUser().getRecommendations(netflix.getShows());
                    ArrayList<Movie> foundTVShows = netflix.getUser().getRecommendations(netflix.getMovies());
                    ArrayList<Movie> all = new ArrayList<>();
                    all.addAll(foundMovies);
                    all.addAll(foundTVShows);
                    if (all.isEmpty()){
                        System.out.println("We still don't have recommendations for you.");
                        System.out.println("Watch and add more movies to your favorites so we can know you better and suggest good movies!");
                    }
                    else {
                        printList(all);
                    }
                    break;
                case 7:
                    netflix.logout();
                    break;
            }
        }
    }

    public static boolean enter(NetflixService netflix){
        Scanner in = new Scanner(System.in);
        System.out.println("1) Log in");
        System.out.println("---------------------");
        System.out.println("2) Create New Account");
        System.out.println("---------------------");
        System.out.println("3) Exit");
        int enter = in.nextInt();
        in.nextLine();
        switch (enter) {
            case 1:
                System.out.println("Please Enter Username: ");
                String username = in.nextLine();
                System.out.println("Please Enter Password: ");
                String password = in.nextLine();
                if (netflix.login(username, password)) {
                    System.out.println("Hi " + username + ". great to have you back!");
                    return true;
                }
                else {
                    System.out.println("There's no user with this information");
                    enter(netflix);
                }
                return true;
            case 2:
                System.out.println("Please Enter Username: ");
                username = in.nextLine();
                System.out.println("Please Enter Password: ");
                password = in.nextLine();
                while (netflix.doesUsernameExist(username)){
                    System.out.println("Username already taken!");
                    System.out.println("Please Enter Username: ");
                    username = in.nextLine();
                    System.out.println("Please Enter Password: ");
                    password = in.nextLine();
                }
                netflix.createAccount(username, password);
                netflix.login(username, password);
                System.out.println("Account Created Successfully");
                return true;
            default:
                return false;
        }
    }
    public static void printList(ArrayList<Movie> movies){
        for (int i = 0; i < movies.size(); i++) {
           System.out.print(i + 1);
           System.out.println(". " + movies.get(i));
        }
    }
    public static ArrayList<Movie> search(NetflixService netflix){
        Scanner in = new Scanner(System.in);
        System.out.println("With what specification do you want to search the movie?");
        System.out.println("1. Name");
        System.out.println("2. Genre");
        System.out.println("3. Release Year");
        int feature = in.nextInt();
        in.nextLine();
        switch (feature){
            case 1:
                System.out.println("Please Enter the Name of The Movie or TV Show You're Looking for:");
                String name = in.nextLine();
                if (netflix.searchByTitle(name) != null){
                    return netflix.searchByTitle(name);
                }
                else {
                    System.out.println("Sorry no movie or TV show with the name " + name + " was found");
                }
                break;
            case 2:
                System.out.println("Please Enter the Genre of The Movie or TV Show You're Looking for:");
                String genre = in.nextLine();
                if (netflix.searchByGenre(genre) != null){
                    return netflix.searchByGenre(genre);
                }
                else {
                    System.out.println("Sorry no movie or TV show with the " + genre + " genre was found");
                }
                break;
            case 3:
                System.out.println("Please Enter the Year the Movie was Released:");
                int year = in.nextInt();
                in.nextLine();
                if (netflix.searchByReleaseYear(year) != null){
                    return netflix.searchByReleaseYear(year);
                }
                else {
                    System.out.println("Sorry no movie or TV show released in " + year + " was found");
                }
                break;
        }
        return null;
    }

    public static ArrayList<Movie> searchInFavorites(NetflixService netflix){
        Scanner in = new Scanner(System.in);
        System.out.println("With what specification do you want to search the movie?");
        System.out.println("1. Name");
        System.out.println("2. Genre");
        System.out.println("3. Release Year");
        int feature = in.nextInt();
        in.nextLine();
        switch (feature){
            case 1:
                System.out.println("Please Enter the Name of The Movie or TV Show You're Looking for:");
                String name = in.nextLine();
                if (netflix.getUser().searchByTitle(name) != null){
                    return netflix.searchByTitle(name);
                }
                else {
                    System.out.println("Sorry no movie or TV show with the name " + name + " was found in liked movies");
                }
                break;
            case 2:
                System.out.println("Please Enter the Genre of The Movie or TV Show You're Looking for:");
                String genre = in.nextLine();
                if (netflix.getUser().searchByGenre(genre) != null){
                    return netflix.searchByGenre(genre);
                }
                else {
                    System.out.println("Sorry no movie or TV show with the " + genre + " genre was found in liked movies");
                }
                break;
            case 3:
                System.out.println("Please Enter the Year the Movie was Released:");
                int year = in.nextInt();
                if (netflix.getUser().searchByReleaseYear(year) != null){
                    return netflix.searchByReleaseYear(year);
                }
                else {
                    System.out.println("Sorry no movie or TV show released in " + year + " was found in liked movies");
                }
                break;
        }
        return null;
    }
}


