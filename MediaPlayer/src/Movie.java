import java.util.*;

public class Movie {

	private String title, director, producer, genre;
	private double rottenTomatoesRating;
	private ArrayList<String> actors;

	Movie(String movieTitle, String movieDirector, String movieProducer, String movieGenre, double movieRottenTomatoesRating, ArrayList<String> movieActors) {
		title = movieTitle;
		director = movieDirector;
		producer = movieProducer;
		genre = movieGenre;
		rottenTomatoesRating = movieRottenTomatoesRating;
		actors = movieActors;
	}

	//	Method to get and set title 
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}
	
	//	Methods to get and set director
	public String getDirector() {
		return director;
	}
	public void setDirector(String d) {
		director = d;
	}

	//	Methods to get and set producer
	public String getProducer() {
		return producer;	
	}
	public void setProducer(String p) {
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
	public ArrayList<String> getActors() {
		return actors;
	}
	public void setActors(ArrayList<String> a) {
		actors = a;
	}

}

	
