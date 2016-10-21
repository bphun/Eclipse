package com.brandonphan.medialib.Media;

import java.util.ArrayList;

public class Movie {

	private String title, genre;
	private Human director, producer;
	private String strDirector, strProducer;
	private double rottenTomatoesRating;
	private ArrayList<Human> actors;
	private ArrayList<String> strActors;

	public Movie(String title, Human director, Human producer, String genre, double rottenTomatoesRating, ArrayList<Human> actors) {
		this.title = title;
		this.director = director;
		this.producer = producer;
		this.genre = genre;
		this.rottenTomatoesRating = rottenTomatoesRating;
		this.actors = actors;
	}
	public Movie(String title, String director, String producer, String genre, double rottenTomatoesRating, ArrayList<String> actors) {
		this.title = title;
		this.strDirector = director;
		this.strProducer = producer;
		this.genre = genre;
		this.rottenTomatoesRating = rottenTomatoesRating;
		this.strActors = actors;
	}

	//	Method to get and set title 
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}
	
	//	Methods to get and set director
	public Human getDirector() {
		return director;
	}
	public void setDirector(Human d) {
		director = d;
	}

	//	Methods to get and set producer
	public Human getProducer() {
		return producer;	
	}
	public void setProducer(Human p) {
		producer = p;
	}

	//	Methods to get and set genre
	public String getGenre() {
		return genre;
	}
	public void setGenre(String g) {
		genre = g;
	}

	//	Methods to get and set rotten tomatoes rating
	public double getRottenTomatoesRating() {
		return rottenTomatoesRating;
	}
	public void setRottenTomatoesRating(double r) {
		rottenTomatoesRating = r;
	}

	//	Methods to get and set a list of actors
	public ArrayList<Human> getActors() {
		return actors;
	}
	public void setActors(Human a) {
		actors.add(a);
	}

	public String getStrProducer() { return strProducer; }

	public String getStrDirector() { return strDirector; }

	public ArrayList<String> getStrActors() { return  strActors; }
}

	
