package org.example.pltw.medialib;

import java.util.ArrayList;

public class Song {

	private String title, album;
	private Human artist;
	private double duration;
	private int rating;
	
	public Song(String title, String  album, Human artist, double duration, int rating) {
		this.title = title;
		this.album = album;
		this.artist = artist;
		this.duration = duration;
		this.rating = rating;
	}

	public static void main(String[] args) {
		ArrayList<Song> top10 = getTop10Songs();

		for (final Song s : top10) {
			System.out.print(s);

		}

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

		for (int i = sortedSongs.size(); i > 0; i--) {
			sortedSongs.remove(i);
		}
		return sortedSongs;
	}

}
