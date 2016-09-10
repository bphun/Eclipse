
package org.example.pltw.medialib;

import java.util.ArrayList;

/**
 * Created by BrandonPhan on 9/9/16.
 */
public class MediaLib {

    ArrayList<Book> books = new ArrayList<Book>();
    ArrayList<Song> songs = new ArrayList<Song>();
    ArrayList<Movie> movies = new ArrayList<Movie>();

    //  Methods to add to books and get books
    public void addBooks(Book b) {
        books.add(b);
    }
    public ArrayList<Book> getBooks() {
        return books;
    }

    //  Methods to add songs and get songs
    public void addSongs(Song s) {
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

}



