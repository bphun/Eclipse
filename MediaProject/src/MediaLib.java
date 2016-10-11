import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by BrandonPhan on 9/9/16.
 */
public class MediaLib {

    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Object> mediaLib = new ArrayList<Object>();

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

    public ArrayList<Object> getMediaLib() {
        return mediaLib;
    }

    /*
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
    
    /*
     * The method below creates a List of String from the List of Song.  Then it passes the List
     * to the MediaFile class and calls the writeSongsFromList method to save them to the file
     */
    public boolean saveSongsFromListToFile(List<Song> songs) {
    	MediaFile mediaFile = new MediaFile();
    	
    	MediaFile.writeSongListToFile(songs, "mymedia");
    	
        return true;
    }
}