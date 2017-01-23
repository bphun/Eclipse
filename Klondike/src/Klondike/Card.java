package Klondike;
import java.awt.Color;
import java.awt.Image;

public class Card {

	private String rank;
	private String suit;
	private boolean isBlack;
	private String fileName;
		
	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
		this.isBlack = isBlack();
		this.fileName = imageFileName();
	}

	public String rank() {
		return rank;
	}

	public String suit() {
		return suit;
	}

	public String color() {
		if (isBlack) {
			return "Black";
		}
		return "Red";
	}

	public String fileName() {
		return fileName;
	}
	
	public boolean matches(Card c) {
		if ((this.rank.equalsIgnoreCase(c.rank)) && 
				(this.suit.equalsIgnoreCase(c.suit)) &&
				(this.isBlack == c.isBlack)) {
			return true;
		}
		return false;
	}
	
	private boolean isBlack() {
		if (this.rank().equalsIgnoreCase("spades") || this.rank().equalsIgnoreCase("clubs")) {
			return true;
		} else if (this.rank().equalsIgnoreCase("diamonds") || this.rank().equalsIgnoreCase("hearts")) {
			return false;
		}
		return false;
	}
	
	private String imageFileName() {
		String str = "cards/";
		if (this == null) {
			return "cards/back1.GIF";
		}
		
		str += this.rank() + this.suit();
		
		str += ".GIF";
		return str;
	}
	
	@Override
	public String toString() {
		if (isBlack) {
			return "Card: " + color() + " " + this.rank + " of " + this.suit;
		} else {
			return "Card: " + color() + " " + this.rank + " of " + this.suit;
		}
	}

}
