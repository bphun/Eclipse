package Klondike;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Pile {

	private static Deck d;

	private List<Card> cards;
	private List<Image> cardImages;
	
	private int pileNumber;
	
	private int size;
	
	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};

	public Pile(int numCards, int pileNumber) {

		if (d == null) {
			d  = new Deck(RANKS, SUITS);			
		}
		
		this.pileNumber = pileNumber;
		
		cards = new ArrayList<>();
		cardImages = new ArrayList<>();
		for (int n = 0; n < numCards; n++) {
			cards.add(d.deal());
		}
		size = cards.size();
	}

	public int size() {
		return size;
	}
	
	public void draw(Graphics2D g) {
		int x = 300;
		int y = 30;
		
		openImages();
		
		for (Image img : cardImages) {
//			switch (pileNumber) {
//			case 0:
//				
//				break;
//			case 1:
//				
//			case 2: 
//			
//			case 3:
//				
//			case 4:
//				
//			case 5:
//				
//			case 6:
//				
//			}
			g.drawImage(img, x, y, null);
		}
	}
	
	private void openImages() {
		for (Card c : cards) {
			try {	
				String cardImageFileName = c.fileName();
				URL cardImgURL = getClass().getResource(cardImageFileName);
				System.out.println(cardImgURL);
				if (cardImgURL != null) {
					Image img = ImageIO.read(cardImgURL);
					cardImages.add(img);
					System.out.println("Opened image ( " + cardImageFileName + " )");
				} 
			} catch (IOException e) {
				System.err.println("Could not open image ( " + c.fileName() + " )");
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		String s = "\n ------ Pile ------ \n";
		for (Card c : cards) {
			s += c.toString();
		}
		return s;
	}
	
}
