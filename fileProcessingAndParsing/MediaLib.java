import java.util.*;
/**
 * Write a description of class MediaLib here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MediaLib
{
    
    
    // instance variables - replace the example below with your own
    private List<Song> songs = new ArrayList();// a bunch of Song Objects will be added
    private final String SONG_FILE_NAME = "mySongs.txt",
                         SAVE_FILE_NAME = "mySavedList.txt";
    /**
     * Constructor for objects of class MediaLib
     */
    public MediaLib()
    {
        // initialise instance variables
        readInSongs();
        printSongs(songs);
        mixUpSongs(songs);
        printSongs(songs);
        saveSongsFromListToFile(songs );
    }

    public void readInSongs(){
        // ask my MediaFile class for all of the song info
        ArrayList<String> songStrings = MediaFile.readInSongInfo(SONG_FILE_NAME);
        // make songs to put in list
        for(String s: songStrings){
            int pipeLoc = s.indexOf('|');
            String title = s.substring(0,pipeLoc);
            int rating = Integer.parseInt(s.substring(pipeLoc+1).trim());
            Song sing = new Song(title.trim(), rating);
            songs.add(sing);
        }
    }
    /*
     * I want the songs to be printed out in a table that goes down the console by song
     * you are encouraged to use the toString method for a Song
     */
    public void printSongs(List<Song> songs){
        System.out.println("Should be printing songs...");
    }
    public void mixUpSongs(List<Song> songs){
        // code to mix up the List.  This is a chance for you to try out different 
        // approaches to shuffling an array.  DO NOT USE Collections.shuffle()
        System.out.println("Should be mixing up the songs...");
    }
    
    /*
     * The method below creates a List of String from the List of Song.  Then it passes the List
     * to the MediaFile class and calls the writeSongsFromList method to save them to the file
     */
    public boolean saveSongsFromListToFile(List<Song> songs){
        // tell the MediaFile class to save songs to the file.  Return true if successful, false if not.
        System.out.println("Should be saving the songs...");
        return false;
    }
}