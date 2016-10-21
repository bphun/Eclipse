//package org.example.pltw.medialib.Activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Parcelable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.example.pltw.medialib.DataManagement.MediaFile;
//import org.example.pltw.medialib.DataManagement.MediaLib;
//import org.example.pltw.medialib.Media.Book;
//import org.example.pltw.medialib.Media.Human;
//import org.example.pltw.medialib.Media.Movie;
//import org.example.pltw.medialib.Media.Song;
//import org.example.pltw.medialib.Strings.Greeting;
//
//public class MainActivity extends AppCompatActivity {
//
//    MediaLib mediaLib = new MediaLib();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Greeting greeting = new Greeting();
//
//        TextView greetingTextView = (TextView) findViewById(R.id.welcomeTextView);
//        greetingTextView.setText(greeting.getGreeting());
//    }
//
//    public void showMedia(View v) {
//        Human testHuman = new Human("Steve Jobs", 2, 24, 1955);
//        MediaFile mediaFile = new MediaFile();
//
//        if (mediaLib.getMediaLib().size() == 0) {
//            Book book = new Book("Harry Potter", testHuman, Book.Genre.FANTASY, null);
//            Movie movie = new Movie("Star Trek", testHuman, testHuman, "Sci-Fi", 9.7, null);
//            Song song = new Song("Dust in The Wind", "Kansas", testHuman, 9.41, 5);
//
//            mediaLib.addBook(book);
//            mediaLib.addMovie(movie);
//            mediaLib.addSong(song);
//        }
//
//        if (!mediaLib.getMediaLib().isEmpty()) {
//
//            ViewGroup linearLayout = (ViewGroup) findViewById(R.id.linearLayout);
//
//            for (int b = 0; b < mediaLib.getBooks().size(); b++) {
//                Button bookButton = new Button(this);
//
//                final Book currentBook = mediaLib.getBooks().get(b);
//
//                bookButton.setText(currentBook.getTitle());
//                bookButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                linearLayout.addView(bookButton);
//            }
//
//            for (int m = 0; m < mediaLib.getMovies().size(); m++) {
//                Button movieButton = new Button(this);
//
//                final Movie currentMovie = mediaLib.getMovies().get(m);
//
//                movieButton.setText(currentMovie.getTitle());
//                movieButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                linearLayout.addView(movieButton);
//            }
//
//            for (int s = 0; s < mediaLib.getSongs().size(); s++) {
//
//                Button songButton = new Button(this);
//
//                final Song currentSong = mediaLib.getSongs().get(s);
//
//                songButton.setText(currentSong.getTitle());
//                songButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                linearLayout.addView(songButton);
//            }
//
//        } else if (mediaLib.getMediaLib().isEmpty()) {
//            Toast.makeText(this, "There isn't any media in your library", Toast.LENGTH_LONG).show();
//        }
//        MediaFile.saveAndClose();
//
//    }
//
//    private void onClick(Object media) {
//
//        String mediaKey = "Media";
//
//        Intent intent = new Intent(MainActivity.this, showMediaInfo.class);
//        intent.putExtra(mediaKey, (Parcelable) media);
//
//        MainActivity.this.startActivity(intent);
//
////        String bundleKey = "Media";
////        Intent intent = new Intent();
////        Bundle bundle = new Bundle();
////
////        bundle.putParcelable(bundleKey, (Parcelable) media);
////
////        intent.putExtras(bundle);
//    }
//}
//
//}
