package org.example.pltw.medialib;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.*;

public class Book implements Parcelable {

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

	//	Create a parcel
	public Book(Parcel parcel) {

		this.title = parcel.readString();
//		this.author = (Human) parcel.readValue(Human.class.getClassLoader());
		this.genre = Genre.valueOf(parcel.readString());
		this.characters = parcel.readArrayList(Human.class.getClassLoader());
	}

	@Override
	public int describeContents() {
		return  0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.title);
//		dest.writeValue(Human.class.getClassLoader());
		dest.writeString(this.genre.name());
		dest.writeArray(this.characters.toArray());
	}

	public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
		public Book createFromParcel(Parcel in) {
			return new Book(in);
		}

		public Book[] newArray(int size) {
			return new Book[size];
		}
	};


}


