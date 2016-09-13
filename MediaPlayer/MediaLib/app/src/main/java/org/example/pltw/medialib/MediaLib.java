
package org.example.pltw.medialib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by BrandonPhan on 9/9/16.
 */
public class MediaLib {

    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    private ArrayList<Object> mediaLib = new ArrayList<Object>();

    //  Methods to add to books and get books
    public void addBook(Book b) {
        books.add(b);

        if (!mediaLib.contains(b)) {
            mediaLib.add(b);
        }
    }
    public ArrayList<Book> getBooks() {
        return books;
    }

    //  Methods to add songs and get songs
    public void addSong(Song s) {
        songs.add(s);

        if (!mediaLib.contains(s)) {
            mediaLib.add(s);
        }
    }
    public ArrayList<Song> getSongs() {
        return songs;
    }

    //  Methods to add movies and get movies
    public void addMovie(Movie m) {
        if (!mediaLib.contains(m)) {
            movies.add(m);
        }
    }
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Object> getMediaLib() {
        return mediaLib;
    }

}