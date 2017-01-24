import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Pile {

	//	A static deck that is shared among all the piles that are in the game
	private static Deck d;

	//	Holds all of the cards that are displayed in a pile
	private List<Card> cards;

	//	The pile's order form left to right (0-6)
	private int pileNumber;
	
	// The number of cards in the pile
	private int size;
	
	//	Used to make sure only the first card and the cards added to the pile are shown
	private int numCardsAdded;

	private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};

	public Pile(int numCards, int pileNumber) {
		/**	
		 * Only initialize the deck if this is the first time creating a pile so that 
		 * they all share one deck -- this is done by making the Deck static
		 */
		if (d == null) {
			d  = new Deck(RANKS, SUITS);			
		}

		this.numCardsAdded = 1;
		this.pileNumber = pileNumber;
		
		/* Initialize the list of cards with the desired number of cards
		 * by calling the decks deal() method
		 */
		cards = new ArrayList<>();
		for (int n = 0; n < numCards; n++) {
			cards.add(d.deal());
		}
		size = cards.size();
	}

	/**
	 * @return number of cards in the pile
	 */
	public int size() {
		return size;
	}
	
	/**
	 * @param index of card that you want to get
	 * @return card in cards list at index 'index'
	 */
	public Card get(int index) {
		return cards.get(index);
	}

	/**
	 * @return List<Card> cards;
	 */
	public List<Card> cards() {
		return cards;
	}

	/*
	 * Adds a list of cards to the cards list
	 * and updates the number of cards added
	 */
	public void addCards(List<Card> cards) {
		this.cards.addAll(cards);
		this.numCardsAdded += cards.size();
	}

	/*
	 * Adds card to the cards list
	 * and updates the number of cards added
	 */
	public void addCard(Card c) {
		this.cards.add(c);
		this.numCardsAdded++;
	}

	/**
	 * @param g is the Graphics2D context
	 * Draws all the cards in the pile
	 */
	public void draw(Graphics2D g) {
		int x = 70;
		int y = 330;
		
		//	Check if there are cards in cards, if not then draw a rounded rect
		if (cards.size() > 0) {
			for (int i = 0; i < cards.size(); i++) {
				Card c = cards.get(i);

				//	Set the x position of the pile depending on the pile's position on the board
				switch (pileNumber) {
				case 1:
					x = 170;
					break;
				case 2: 
					x = 270;
					break;
				case 3:
					x = 370;
					break;
				case 4:
					x = 470;
					break;
				case 5:
					x = 570;
					break;
				case 6:
					x = 670;
					break;
				}

				/*
				 * Check if the we aren't drawing the last card, 
				 * if we are then don't draw the back of the card, 
				 * otherwise draw the back of the card by using the boolean
				 * argument in c.draw(Graphics2D g, int x, int y, boolean drawBackOfCard)
				 */	
				if (i < cards.size() - numCardsAdded) {
					c.draw(g, x, y, true);
				} else if (i < cards.size()) {
					c.draw(g, x, y, false);
				}
				y += 19;
			}
		} else if (cards.size() == 0) { 	
			g.drawRoundRect(x, y, 90, 150, 25, 50);
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
