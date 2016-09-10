package org.example.pltw.medialib;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by BrandonPhan on 9/9/16.
 */
public class MediaLib {

    public ArrayList<Book> books = new ArrayList<Book>();
    public ArrayList<Song> songs = new ArrayList<Song>();
    public ArrayList<Movie> movies = new ArrayList<Movie>();

    ArrayList<Object> mediaLib = new ArrayList<>();

    //  Methods to add to books and get books
    public void addBook(Book b) {
        books.add(b);
    }
    public ArrayList<Book> getBooks() {
        return books;
    }

    //  Methods to add songs and get songs
    public void addSong(Song s) {
        songs.add(s);
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }

    //  Methods to add movies and get movies
    public void addMovie(Movie m) {
        movies.add(m);
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    //  Method used to update media list
    public void updateMediaLib() {
        for (int b = 0; b < books.size(); b++) {
            mediaLib.add(books.get(b));
        }

        for (int s = 0; s < songs.size(); s++) {
            mediaLib.add(songs.get(s));
        }

        for (int m = 0; m < movies.size(); m++) {
            mediaLib.add(movies.get(m));
        }

    }

}
