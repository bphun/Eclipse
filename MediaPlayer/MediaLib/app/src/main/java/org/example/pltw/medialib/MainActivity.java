package org.example.pltw.medialib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MediaLib mediaLib = new MediaLib();
    int buttonTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Greeting greeting = new Greeting();

        TextView greetingTextView = (TextView) findViewById(R.id.welcomeTextView);
        greetingTextView.setText(greeting.getGreeting());
    }

    public void showMedia(View v) {
        TextView outputText = (TextView) findViewById(R.id.mediaLibText);

        Human testHuman = new Human("Steve Jobs", 2, 24, 1955);

        if (mediaLib.getMediaLib().size() == 0) {
            Book book = new Book("Harry Potter", testHuman, Book.Genre.FANTASY, null);
            Movie movie = new Movie("Star Trek", testHuman, testHuman, "Sci-Fi", 9.7, null);
            Song song = new Song("Dust in The Wind", "Kansas", testHuman, 9.41);

            mediaLib.addBook(book);
            mediaLib.addMovie(movie);
            mediaLib.addSong(song);
        }

        if (!mediaLib.getMediaLib().isEmpty()) {

            ViewGroup linearLayout = (ViewGroup) findViewById(R.id.linearLayout);

            for (int b = 0; b < mediaLib.getBooks().size(); b++) {
                Button bookButton = new Button(this);

                Book currentBook = mediaLib.getBooks().get(b);

                bookButton.setText(currentBook.getTitle());
                bookButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                bookButton.setTag(buttonTag, currentBook);

                linearLayout.addView(bookButton);

                buttonTag++;

//                outputText.append("Book: " + mediaLib.getBooks().get(b).getTitle() + "\n");
            }

            for (int m = 0; m < mediaLib.getMovies().size(); m++) {
                Button movieButton = new Button(this);

                Movie currentMovie = mediaLib.getMovies().get(m);

                movieButton.setText(currentMovie.getTitle());
                movieButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                movieButton.setTag(buttonTag, currentMovie);

                linearLayout.addView(movieButton);

                buttonTag++;

//                outputText.append("Movie: " + mediaLib.getMovies().get(m).getTitle() + "\n");
            }

            for (int s = 0; s < mediaLib.getSongs().size(); s++) {

                Button songButton = new Button(this);

                Song currentSong = mediaLib.getSongs().get(s);

                songButton.setText(currentSong.getTitle());
                songButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                songButton.setTag(buttonTag, currentSong);
                
                linearLayout.addView(songButton);

                buttonTag++;

//                outputText.append("Song: " + mediaLib.getSongs().get(s).getTitle() + "\n");
            }

        } else if (mediaLib.getMediaLib().isEmpty()) {
            outputText.setText("There isn't any media in your library");
        }
    }

    private void onMediaButtonClick(View v) {

    }


}