import java.util.ArrayList;

public class Song {

	private String title, album;
	public String artist;
	private double duration;
	private int rating;
	
	ArrayList<String> list = new ArrayList<String>();
	
	public Song(String title, String  album, String artist, double duration, int rating) {
		this.title = title;
		this.album = album;
		this.artist = artist;
		this.duration = duration;
		this.rating = rating;
	}
	public Song(String title) {
		this.title = title;
	}
	
	//	Methods to get and set title	
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}

	//	Methods to get and set album
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String a) {
		title = a;
	}
	
	//	Methods to get and set artist
	public String getArtist() {
		return artist;
	}
	public void setArtist(String a) {
		artist = a;
	}
	
	//	Methods to get and set artist
	public double getDuration() {
		return duration;
	}
	public void getDuration(double d) {
		duration = d;
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int r) {
		this.rating = r;
	}
	
	public ArrayList<Song> sortSongsByRating() {
		Sort sorter = new Sort();

		ArrayList<Song> songs = new MediaLib().getSongs();
		Song[] unsortedArray = new Song[songs.size()];

		for (final Song s : songs) {
			unsortedArray[unsortedArray.length] = s;
		}

		return sorter.mergeSort(unsortedArray);
	}

	public ArrayList<Song> getTop10Songs() {
		ArrayList<Song> sortedSongs = sortSongsByRating();

		int i = sortedSongs.size();
		while (sortedSongs.size() > 0) {
			sortedSongs.remove(i);
			i--;
		}

		return sortedSongs;
	}
	
	public ArrayList<Song> scrambleSongList(ArrayList<Song> unscrambledSongs) {
		ArrayList<Song> scrambledSongs = new ArrayList<Song>();
		
		
		
		return scrambledSongs;
	}
}
