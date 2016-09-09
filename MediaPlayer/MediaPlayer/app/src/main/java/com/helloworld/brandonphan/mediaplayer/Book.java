package com.helloworld.brandonphan.mediaplayer;

import java.util.*;

public class Book {

	private String title, author, genre;
	private ArrayList<String> characters;

	Book(String bookTitle, String bookAuthor, String bookGenre, ArrayList<String> bookCharacters) {
		title = bookTitle;
		author = bookAuthor;
		genre = bookGenre;
		characters = bookCharacters;
	}

	//	Methods to get and set title
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}

	//	Methods to get and set author
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String a) {
		author = a;
	}

	//	Methods to get and set genre
	public String getGenre() {
		return genre;
	}
	public void setGenre(String g) {
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


