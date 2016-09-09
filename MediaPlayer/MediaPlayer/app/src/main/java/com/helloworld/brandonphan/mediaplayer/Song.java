package com.helloworld.brandonphan.mediaplayer;

public class Song {

	private String title, album, artist;
	private double duration;
	
	public Song(String songTitle, String  songAlbum, String songArtist, double songDuration) {
		title = songTitle;
		album = songAlbum;
		artist = songArtist;
		duration = songDuration;
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
}
