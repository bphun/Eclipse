package org.example.pltw.medialib;

import java.util.*;

public class Movie {

	public String title, genre;
	public Human director, producer;
	public double rottenTomatoesRating;
	public ArrayList<Human> actors;

	Movie(String title, Human director, Human producer, String genre, double rottenTomatoesRating, ArrayList<Human> actors) {
		this.title = title;
		this.director = director;
		this.producer = producer;
		this.genre = genre;
		this.rottenTomatoesRating = rottenTomatoesRating;
		this.actors = actors;
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

}

	
