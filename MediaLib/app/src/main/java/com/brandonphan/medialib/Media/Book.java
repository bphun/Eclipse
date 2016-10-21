package com.brandonphan.medialib.Media;

import java.util.ArrayList;

public class Book {

	public enum Genre {
		CLASSIC,
		CRIME,
		DRAMA,
		FABLE,
		FAIRY_TALE,
		FAN_FICTION,
		FANTASY,
		FICTION,
		FOLKLORE,
		HISTORICAL_FICTION,
		HORROR,
		HUMOR,
		LEGEND,
		MYSTERY,
		MYTHOLOGY,
		PICTURE_BOOK,
		REALISTIC_FICTION,
		SCI_FI,
		SHORTY_STORY,
		SUSPENSE,
		BIOGRAPHY,
		ESSAY,
		MEMOIR,
		SPEECH,
		TEXTBOOK;

		public static Genre parseGenre(String s) {
			if (s != null) {
				for (final  Genre g : Genre.values()) {
					if (s.equalsIgnoreCase(g.name())) {
						return g;
					}
				}
			}
			return null;
		}

	}

	private String title;
	private Human author;
	private String strAuthor;
	private ArrayList<String> strCharacters;
	private ArrayList<Human> characters;
	private Genre genre;

	public Book(String title, Human author, Genre genre, ArrayList<Human> characters) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.characters = characters;
	}
	public Book(String title, String author, Genre genre, ArrayList<String> characters) {
		this.title = title;
		this.strAuthor = author;
		this.genre = genre;
		this.strCharacters = characters;
	}

	//	Methods to get and set title
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}

	//	Methods to get and set author
	public Human getAuthor() {
		return author;
	}
	public void setAuthor(Human a) {
		author = a;
	}

	//	Methods to get and set genre
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre g) {
		genre = g;
	}

	//	Methods to get list of characters and add characters
	public ArrayList<Human> getCharacters() {
		return characters;
	}
	public void addCharacters(Human c) {
		characters.add(c);
	}

	public String getStrAuthor() { return strAuthor; }
	public ArrayList<String> getStrCharacters() { return strCharacters; }

}


