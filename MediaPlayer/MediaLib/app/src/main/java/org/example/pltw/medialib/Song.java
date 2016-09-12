package org.example.pltw.medialib;

public class Song {

	public String title, album;
	public Human artist;
	public double duration;
	
	public Song(String title, String  album, Human artist, double duration) {
		this.title = title;
		this.album = album;
		this.artist = artist;
		this.duration = duration;
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
}
