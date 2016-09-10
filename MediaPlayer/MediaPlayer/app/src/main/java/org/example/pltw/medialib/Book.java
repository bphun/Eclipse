package org.example.pltw.medialib;

import java.util.*;

public class Book {

	public enum Genre {
		CLASSIC,
		CRIME,
		DRAMA,
		FABLE,
		FAIRY_TALE,
		FANFICTION,
		FANTASY,
		FICTION,
		FOLKLORE,
		HISTORICAL_FICTION,
		HORRORY,
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
		TEXTBOOK
	}

	public String title;
	public Human author;
	public ArrayList<String> characters;
	public Genre genre;

	Book(String title, Human author, Genre genre, ArrayList<String> characters) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.characters = characters;
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
	public ArrayList<String> getCharacters() {
		return characters;
	}
	public void setCharacters(String c) {
		characters.add(c);
	}

}


