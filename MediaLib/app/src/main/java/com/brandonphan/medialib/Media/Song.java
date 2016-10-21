package com.brandonphan.medialib.Media;

import com.brandonphan.medialib.Sort;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Song {

	private String title, album;
	private Human artist;
	private String strArtist;
	private double duration;
	private int rating;
	private int yearOfProduction;

//	public static void main(String[] args) {
//		ArrayList<Song> top10 = getTop10Songs();
//	}

	public Song(String title, String album, Human artist, double duration, int rating) {
		this.title = title;
		this.album = album;
		this.artist = artist;
		this.duration = duration;
		this.rating = rating;
	}
	public Song(String title, String album, String artist, double duration, int rating) {
		this.title = title;
		this.album = album;
		strArtist = artist;
		this.duration = duration;
		this.rating = rating;
	}
	public Song(String title, Human cre, double dur, int rate, int year) {
		this.title = title;
		this.duration = dur;
		this.artist = cre;
		this.rating = rate;
		this.yearOfProduction = year;
	}
	public Song(String title, double dur) {
		// better line here is below
		// this(title, null, dur, -1, -1);// which calls the constructor above

		// initialise instance variables
		this.title = title;
		this.duration = dur;
	}

	public Song(String title, int rat) {
		this(title, null, -1, rat, -1);// pass in obvious impossible values for other data
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
	public Human getArtist() {
		return artist;
	}
	public void setArtist(Human a) {
		artist = a;
	}
	
	//	Methods to get and set artist
	public double getDuration() {
		return duration;
	}
	public void getDuration(double d) {
		duration = d;
	}

	public int getRating() { return  rating; }
	public void setRating(int r) { this.rating = r; }

	public String getStrArtist() { return strArtist; }

	public static Song parseSong(String s) {
		if ((s == null) || (s == "")) { return null; }

		String title = s.substring(s.indexOf("Title: ") + 7, s.indexOf(" by "));
		Human creator = new Human(s.substring(s.indexOf("by "), s.indexOf(", year: ") - 12));
		double duration = Double.parseDouble(s.substring(s.indexOf("time: ") + 6, s.indexOf(", year: ")));
		int year = Integer.parseInt(s.substring(s.indexOf(", year: ") + 8, s.indexOf(", rating: ")));
		int rating = Integer.parseInt(s.substring(s.indexOf(", rating: ") + 10));

		return new Song(title, creator, duration, rating, year);
	}

	@Override
	public String toString(){
		String s = "Song - ";
		s += "Title: " + this.title + " by " + artist + ", time: " + getDuration() + ", year: " + yearOfProduction + ", rating: " + rating;
		return s;

	}

	public static ArrayList<Song> sortSongsByRating() {
		Sort sorter = new Sort();

		ArrayList<Song> songs = new MediaLib().getSongs();
		Song[] unsortedArray = new Song[songs.size()];

		for (final Song s : songs) {
			unsortedArray[unsortedArray.length] = s;
		}

		return sorter.mergeSort(unsortedArray);
	}

	public static ArrayList<Song> getTop10Songs() {
		ArrayList<Song> sortedSongs = sortSongsByRating();

		int i = sortedSongs.size();
		while (sortedSongs.size() > 0) {
			sortedSongs.remove(i);
			i--;
		}

		return sortedSongs;
	}
}
