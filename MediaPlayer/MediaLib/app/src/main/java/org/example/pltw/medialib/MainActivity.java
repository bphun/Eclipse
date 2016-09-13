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

        TextView welcomeText = (TextView) findViewById(R.id.welcomeTextView);
        welcomeText.setText(greeting.getGreeting());
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

                bookButton.setText(mediaLib.getBooks().get(b).getTitle());
                bookButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                bookButton.setTag(buttonTag, null);

                buttonTag++;
                linearLayout.addView(bookButton);

//                outputText.append("Book: " + mediaLib.getBooks().get(b).getTitle() + "\n");
            }

            for (int m = 0; m < mediaLib.getMovies().size(); m++) {
                Button movieButton = new Button(this);

                movieButton.setText(mediaLib.getMovies().get(m).getTitle());
                movieButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(movieButton);

                movieButton.setTag(buttonTag, null);

                buttonTag++;
//                outputText.append("Movie: " + mediaLib.getMovies().get(m).getTitle() + "\n");
            }

            for (int s = 0; s < mediaLib.getSongs().size(); s++) {

                Button songButton = new Button(this);

                songButton.setText(mediaLib.getSongs().get(s).getTitle());
                songButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                linearLayout.addView(songButton);

                songButton.setTag(buttonTag, null);

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