import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Card {

	private String rank;
	private String suit;
	private String color;
	private String fileName;
		

	//	The (X, Y) coordinate of the card on the board
	private int x;
	private int y;

	private Image img;

	private static final int IMG_WIDTH = 71;
	private static final int IMG_HEIGHT = 96;

	//	The back of card image that is used if we are drawing the back of a card
	private static final String BACK_CARD_FILE_NAME = "cards/back1.GIF";

	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
		this.color = isBlack();
		this.fileName = imageFileName();
	}

	public String rank() {
		return rank;
	}

	public String suit() {
		return suit;
	}

	public String color() {
		return this.color;
	}

	public String fileName() {
		return fileName;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	private void setY(int y) {
		this.y = y;
	}

	public boolean matches(Card c) {
		if ((this.rank.equalsIgnoreCase(c.rank)) && 
				(this.suit.equalsIgnoreCase(c.suit)) &&
				(this.color.equals(c.color))) {
			return true;
		}
		return false;
	}
	
	public boolean isLessThan(Card c) {
		int num1 = 0;
		int num2 = 0;
		try {
			num1 = Integer.parseInt(this.rank);
			num2 = Integer.parseInt(c.rank());
		} catch (NullPointerException e) {
			System.out.println("Err");
		}
		return num1 < num2;
	}

	public String isBlack() {
		if (this.rank().equalsIgnoreCase("spades") || this.rank().equalsIgnoreCase("clubs")) {
			return "black";
		} else if (this.rank().equalsIgnoreCase("diamonds") || this.rank().equalsIgnoreCase("hearts")) {
			return "red";
		}
		return "";
	}
	
	/**
	 * @param x coordinate of the click
	 * @param y coordiante of the click
	 * Determines if the click that was just executed was at a clickable card
	 * if a clickable card was clicked, then it will return an array in the form of 
	 * [Boolean, Card], the
	 */
	public Object[] containsPoint(int x, int y) {
		//	The origin of the image is at the top left hand corner so min values are at the origin or the global X and Y variables
		final int MIN_Y = this.y; 
		final int MAX_Y = this.y + IMG_HEIGHT;

		final int MIN_X = this.x;
		final int MAX_X = this.x + IMG_WIDTH;
			
		Object[] data = new Object[2];

		if (((x == MIN_X) && (y == MIN_Y)) || ((x == MAX_X) && (y == MAX_Y))) {
			data[0] = true;
			data[1] = this;
			return data;
		} else if (((x > MIN_X) && (x < MAX_X)) && ((y > MIN_Y) && (y < MAX_Y))) {
			data[0] = true;
			data[1] = this;
			return data;
		}
		

		return null;
	}

	/**
	 * @return the file name of this card by combining the directory, rank, and suit
	 */
	private String imageFileName() {
		String str = "cards/";
		
		str += this.rank() + this.suit();
		str += ".GIF";

		return str;
	}

	/**
	 * @param drawBackOfCard tells the method if we need to open the image of the back of card
	 *			if not then it will open the appropriate image
	 * Opens the image of this card and returns it
	 */
	private void openImage(boolean drawBackOfCard) {
		try {	
			if (drawBackOfCard == true) {
				URL cardImgURL = getClass().getResource(BACK_CARD_FILE_NAME);
				if (cardImgURL != null) {
					img = ImageIO.read(cardImgURL);
					// img = img.getScaledInstance(img.getWidth(null) , img.getHeight(null), Image.SCALE_DEFAULT);
				}
			} else {
				URL cardImgURL = getClass().getResource(fileName);
				if (cardImgURL != null) {
					img = ImageIO.read(cardImgURL);
					// img = img.getScaledInstance(img.getWidth(null) , img.getHeight(null), Image.SCALE_DEFAULT);
				}
			}
		} catch (IOException e) {
			System.err.println("Could not open image ( " + fileName() + " )");
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g, int x, int y, boolean drawBackOfCard) {
		openImage(drawBackOfCard);
		g.drawImage(img, x, y, null);
		//	Set the (X, Y) coordinates of the card so that it can be used to determine fi teh card has been clicked
		setX(x);
		setY(y);
	}

	@Override
	public String toString() {
		return "Card: " + color() + " " + this.rank + " of " + this.suit;
	}

}
