import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JComponent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Card {

	//	The (X, Y) coordinate of the card on the board
	private int image_X;
	private int image_Y;

	private String rank;
	private String suit;
	private String color;
	private String fileName;		

	private Image img;

	private boolean faceUp;
	private static Image backImage;

	private static final int IMG_WIDTH = 71;
	private static final int IMG_HEIGHT = 97;

	private boolean isSelected;
	private Image selectedImage;
	private final String selectedImageFileName;

	//	The back of card image that is used if we are drawing the back of a card
	private static final String BACK_CARD_FILE_NAME = "cards/back1.GIF";

	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
		faceUp = false;
		isSelected = false;
		selectedImageFileName = "cards/" + this.rank + this.suit + "S" + ".GIF";
		this.fileName = imageFileName();
		this.color = getColor();
		this.openImage();
	}

	public boolean faceUp(){
		return faceUp;
	}

	public boolean faceDown(){
		return !faceUp;
	}

	public void flip(){
		faceUp = !faceUp;
	}

	public void setSelected() {
		if (!isSelected) {
			this.isSelected = true;
		} else {
			this.isSelected = false;
		}
	}

	public boolean isSelected() {
		return isSelected;
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

	public void setX(int x) {
		this.image_X = x;
	}

	public int x() {
		return this.image_X;
	}

	public int y() {
		return this.image_Y;
	}

	public void setY(int y) {
		this.image_Y = y;
	}

	public int intSuit() {
		if (this.rank.equalsIgnoreCase("jack") || this.rank.equalsIgnoreCase("queen") || this.rank.equalsIgnoreCase("king")) {
			return 10;
		} else if (this.rank.equalsIgnoreCase("ace")) {
			return 1;
		} else {
			return Integer.parseInt(this.rank);
		}
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

	public String getColor() {
		if (this.suit.equalsIgnoreCase("spades") || this.suit.equalsIgnoreCase("clubs")) {
			return "black";
		} else if (this.suit.equalsIgnoreCase("diamonds") || this.suit.equalsIgnoreCase("hearts")) {
			return "red";
		}
		return "";
	}
	
	/**
	 * @param x coordinate of the click
	 * @param y coordiante of the click
	 * Determines if the click that was just executed was at a clickable card
	 * if a clickable card was clicked, then it will return the card
	 */
	public Card containsPoint(int x, int y) {
		Rectangle imageBounds = new Rectangle(image_X, image_Y, IMG_WIDTH, IMG_HEIGHT);
		if (imageBounds.contains(new Point(x,y))){
    		return this;
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
	private void openImage() {
		try {		
			URL cardImgURL = getClass().getResource(fileName);
			if (cardImgURL != null) {
				img = ImageIO.read(cardImgURL);
				// img = img.getScaledInstance(img.getWidth(null) , img.getHeight(null), Image.SCALE_DEFAULT);
			}
		} catch (IOException e) {
			System.err.println("Could not open image ( " + fileName() + " )");
			e.printStackTrace();
		}

		if (backImage == null){
			try {	
				URL cardImgURL = getClass().getResource(BACK_CARD_FILE_NAME);
				if (cardImgURL != null) {
					backImage = ImageIO.read(cardImgURL);
				}
			} catch (IOException e) {
				System.err.println("Could not open image ( " + fileName() + " )");
				e.printStackTrace();
			}
		}

		try {		
			URL cardImgURL = getClass().getResource(selectedImageFileName);
			if (cardImgURL != null) {
				selectedImage = ImageIO.read(cardImgURL);
				// img = img.getScaledInstance(img.getWidth(null) , img.getHeight(null), Image.SCALE_DEFAULT);
			}
		} catch (IOException e) {
			System.err.println("Could not open image ( " + fileName() + " )");
			e.printStackTrace();
		}
		
	}

	public void draw(Graphics2D g, int x, int y) {
		setX(x);
		setY(y);
		
		if ((faceUp) && (isSelected)) {
			g.drawImage(this.selectedImage, x, y, null);
		} else if ((faceUp) && (!isSelected)) {
			g.drawImage(img, x, y, null);
		} else {
			g.drawImage(this.backImage, x, y, null);
		}
	}

	@Override
	public String toString() {
		return "Card: " + color + " " + this.rank + " of " + this.suit;
	}

}
