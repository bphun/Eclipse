import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

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

}