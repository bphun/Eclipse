
package com.brandonphan.medialib.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by BrandonPhan on 9/9/16.
 */
public class MediaLib {

    private static ArrayList<Book> books = new ArrayList<Book>();
    private static ArrayList<Song> songs = new ArrayList<Song>();
    private static ArrayList<Movie> movies = new ArrayList<Movie>();

    private static ArrayList<Object> mediaLib = new ArrayList<Object>();


    public MediaLib() {
        readInBooks();
        readInSongs();
        readInMovies();
        updateMediaLib();


        for (final Song s : songs) {
            System.out.print("Title: " + s.getTitle() + "\n");
            System.out.print("Album: " + s.getAlbum() + "\n");
            System.out.print("Artist: " + s.getStrArtist() + "\n");
            System.out.print("Duration: " + s.getDuration() + "\n");
            System.out.print("Rating: " + s.getRating() + "\n");
            System.out.print("\n");
        }

        for (final Movie m : movies) {
            System.out.print("Title: " + m.getTitle() + "\n");
            System.out.print("Director: " + m.getStrDirector() + "\n");
            System.out.print("Producer: " + m.getStrProducer() + "\n");
            System.out.print("Genre: " + m.getGenre() + "\n");
            System.out.print("Rating: " + m.getRottenTomatoesRating() + "\n");
            System.out.print("actors: " + m.getStrActors() + "\n");
            System.out.print("\n");
        }

        for (final Book b : books) {
            System.out.print("Title: " + b.getTitle() + "\n");
            System.out.print("Author: " + b.getStrAuthor() + "\n");
            System.out.print("Genre: " + b.getGenre() + "\n");
            System.out.print("Characters: " + b.getStrCharacters() + "\n");
        }

        System.out.print(books.size() + "," + songs.size() + "," + movies.size());
    }

    public static void main(String[] args) {

        readInSongs();

        for (final Song s : songs) {
            System.out.print("Title: " + s.getTitle() + "\n");
            System.out.print("Album: " + s.getAlbum() + "\n");
            System.out.print("Artist: " + s.getStrArtist() + "\n");
            System.out.print("Duration: " + s.getDuration() + "\n");
            System.out.print("Rating: " + s.getRating() + "\n");
            System.out.print("\n");
        }

        readInMovies();

        for (final Movie m : movies) {
            System.out.print("Title: " + m.getTitle() + "\n");
            System.out.print("Director: " + m.getStrDirector() + "\n");
            System.out.print("Producer: " + m.getStrProducer() + "\n");
            System.out.print("Genre: " + m.getGenre() + "\n");
            System.out.print("Rating: " + m.getRottenTomatoesRating() + "\n");
            System.out.print("actors: " + m.getStrActors() + "\n");
            System.out.print("\n");
        }

        readInBooks();
        for (final Book b : books) {
            System.out.print("Title: " + b.getTitle() + "\n");
            System.out.print("Author: " + b.getStrAuthor() + "\n");
            System.out.print("Genre: " + b.getGenre() + "\n");
            System.out.print("Characters: " + b.getStrCharacters() + "\n");
        }
    }

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

    public static ArrayList<Book> readInBooks() {
        // ask my MediaFile class for all of the book info

        System.out.print("Reading in books" + "\n");

        ArrayList<String> movieStrings = MediaFile.readInMediaInfo("books.txt");

        if (movieStrings != null) {
            for (final String songInfo : movieStrings) {
                String[] regex = songInfo.split("\\|");

                String title = "";
                String author = "";
                Book.Genre genre = null;
                ArrayList<String> characters = new ArrayList<String>();

                for (final String n : regex) {
                    if (n.contains("Title: ")) {
                        title = n.substring(7);
                    } else if (n.contains("Author: ")) {
                        author = n.substring(8);
                    } else if (n.contains("Genre: ")) {
                        genre = Book.Genre.parseGenre(n.substring(7));
                    } else if (n.contains("Character: ")) {
                        characters.add(n.substring(11));
                    }
                }

                if ((title != "") && (author != "") && (genre != null) && (characters != null)) {
                    Book book = new Book(title, author, genre, characters);
                    books.add(book);
                }
            }
        }
        return books;
    }

    public static ArrayList<Movie> readInMovies() {
        // ask my MediaFile class for all of the movie info
        ArrayList<String> movieStrings = MediaFile.readInMediaInfo("movies.txt");

        System.out.print("Reading in movies" + "\n");

        if (movieStrings != null) {
            for (final String songInfo : movieStrings) {
                String[] regex = songInfo.split("\\|");
                String title = "";
                String director = "";
                String producer = "";
                String genre = "";
                double rating = 0;
                ArrayList<String> actors = new ArrayList<String>();

                for (final String n : regex) {
                    if (n.contains("Title: ")) {
                        title = n.substring(7);
                    } else if (n.contains("Director: ")) {
                        director = n.substring(10);
                    } else if (n.contains("Producer: ")) {
                        producer = n.substring(10);
                    } else if (n.contains("Genre: ")) {
                        genre = n.substring(7);
                    } else if (n.contains("Rating: ")) {
                        rating = Double.parseDouble(n.substring(8));
                    } else if (n.contains("Actor: ")) {
                        actors.add(n.substring(7));
                    }
                }

                if ((title != "") && (director != "") && (producer != "") && (genre != "") && (rating != 0) && (actors != null)) {
                    Movie movie = new Movie(title, director, producer, genre, rating, actors);
                    movies.add(movie);
                }
            }
        }
        
        return movies;
    }

    public static ArrayList<Song> readInSongs(){
        // ask my MediaFile class for all of the song info
        ArrayList<String> songStrings = MediaFile.readInMediaInfo("songs.txt");

        System.out.print("Reading in songs" + "\n");

        if (songStrings != null) {
            for (final String songInfo : songStrings) {
                String[] regex = songInfo.split("\\|");
                String title = "";
                String album = "";
                String artist = "";
                double duration = 0.0;
                int rating = 0;

                for (final String n : regex) {
                    if (n.contains("Title: ")) {
                        title = n.substring(7);
                    } else if (n.contains("Album: ")) {
                        album = n.substring(7);
                    } else if (n.contains("Artist: ")) {
                        artist = n.substring(8);
                    } else if (n.contains("Duration: ")) {
                        duration = Double.parseDouble(n.substring(10));
                    } else if (n.contains("Rating: ")) {
                        rating = Integer.parseInt(n.substring(8));
                    }
                }

                if ((title != "") && (album != "") && (artist != "") && (duration != 0.0) && (rating != 0)) {
                    Song song = new Song(title, album,artist,duration,rating);
                    songs.add(song);
                }
            }
        }

        return songs;
    }

    public static void updateMediaLib() {
        for (final Song s : songs) {
            if (!mediaLib.contains(s)) {
                mediaLib.add(s);
            }
        }
        for (final Book b : books) {
            if (!mediaLib.contains(b)) {
                mediaLib.add(b);
            }
        }

        for (final Movie m : movies) {
            if (!mediaLib.contains(m)) {
                mediaLib.add(m);
            }
        }
    }

    /**
    * I want the songs to be printed out in a table that goes down the console by song
    * you are encouraged to use the toString method for a Song
    */
    public void printSongs(List<Song> songs) {
        for (final Song s : songs) {
            System.out.print(s.toString() + "\n");
        }
    }

    public void mixUpSongs(List<Song> songs){
        // code to mix up the List.  This is a chance for you to try out different
        // approaches to shuffling an array.  DO NOT USE Collections.shuffle()
        if (songs.size() <= 1) { return; }

        Random random = new Random();

        for (int i = songs.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            Song s = songs.get(index);

            songs.set(index, songs.get(i));
            songs.set(i, s);
        }
    }

    /**
     * The method below creates a List of String from the List of Song.  Then it passes the List
     * to the MediaFile class and calls the writeSongsFromList method to save them to the file
     */
    public boolean saveSongsFromListToFile(List<Song> songs) {
        MediaFile mediaFile = new MediaFile();
        MediaFile.writeSongListToFile(songs, "songs");
        return true;
    }
}