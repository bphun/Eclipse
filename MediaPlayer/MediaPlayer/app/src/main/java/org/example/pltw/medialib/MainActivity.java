package org.example.pltw.medialib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MediaLib mediaLib = new MediaLib();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeText = (TextView) findViewById(R.id.welcomeTextView);
        welcomeText.setText("Welcome to your media library");
    }


    /**
     * This method is called when the Show Contents button is clicked
     **/
    public void showMedia(View v) {

        TextView outputText = (TextView) findViewById(R.id.mediaLibText);
        ListView mediaLibListView = (ListView) findViewById(R.id.mediaLibListView);

        Human testHuman = new Human("Steve Jobs", 2, 24, 1955);

        Book book = new Book("Harry Potter", testHuman, Book.Genre.FANTASY, null);
        Movie movie = new Movie("Star Trek", testHuman, testHuman, "Sci-Fi", 9.7, null);
        Song song = new Song("asdfa", "asdf", testHuman, 9.41);

        mediaLib.addBook(book);
        mediaLib.addMovie(movie);
        mediaLib.addSong(song);

//        ArrayAdapter adapter = new ArrayAdapter<Object>(this, R.layout.activity_main, mediaLib.mediaLib);
//
//        mediaLibListView.setAdapter(adapter);

        if (!mediaLib.mediaLib.isEmpty()) {
            for (int b = 0; b < mediaLib.books.size(); b++) {
                outputText.append("Book: " + mediaLib.books.get(b).getTitle() + "\n");
            }

            for (int m = 0; m < mediaLib.movies.size(); m++) {
                outputText.append("Movie: " + mediaLib.movies.get(m).getTitle() + "\n");
            }

            for (int s = 0; s < mediaLib.songs.size(); s++) {
                outputText.append("Songs: " + mediaLib.songs.get(s).getTitle() + "\n");
            }

        } else {
            outputText.setText("None");
        }
    }
}
