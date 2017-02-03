import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Pile {

	//	A static deck that is shared among all the piles that are in the game
	public static Deck d;

	//	Holds all of the cards that are displayed in a pile
	private List<Card> cards;

	//	The pile's order form left to right (0-6)
	private int pileNumber;
	
	private int xCoord;
	private int yCoord;

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
		// super(RANKS, SUITS);
		/**	
		 * Only initialize the deck if this is the first time creating a pile so that 
		 * they all share one deck -- this is done by making the Deck static
		 */
		if (d == null) {
			d = new Deck(RANKS, SUITS);			
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
		
		xCoord = cards.get(0).x();
		yCoord = cards.get(0).y();

		updateSize();
	}

	public Pile(int numCards) {
		if (d == null) {
			d = new Deck(RANKS, SUITS);
		}

		cards = new ArrayList<>();
		deal();
		updateSize();
	}

	public Pile() {
		if (d == null) {
			d = new Deck(RANKS, SUITS);
		}

		cards = new ArrayList<>();
		for (int n = 0; n < d.size(); n++) {
			cards.add(d.deal());
		}

		updateSize();
	}

	public boolean noCards() {
		return size == 0;
	}

	/**
	 * @return number of cards in the pile
	 */
	public int size() {
		updateSize();
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

	public void deal() {
		cards.add(d.deal());
		updateSize();
	}

	public void remove(List<Card> cards) {
		this.cards.removeAll(cards);
		updateSize();
	}

	/*
	 * Adds a list of cards to the cards list
	 * and updates the number of cards added
	 */
	public void addCards(List<Card> cards) {
		this.cards.addAll(cards);
		this.numCardsAdded += cards.size();
		updateSize();
	}

	public void returnCards() {
		this.d.returnCardsToDeck(this.cards);
		this.cards.clear();
	}

	/*
	 * Adds card to the cards list
	 * and updates the number of cards added
	 */
	public void addCard(Card c) {
		this.cards.add(c);
		this.numCardsAdded++;
		updateSize();
	}

	public int numCardsAdded() {
		return numCardsAdded;
	}

	private void updateSize() {
		this.size = this.cards.size();
	}

	public boolean empty() {
		return d.isEmpty();
	}

	/**
	 * @param g is the Graphics2D context
	 * Draws all the cards in the pile
	 */
	public void draw(Graphics2D g, int mode) {
		int x;
		int y;

		switch (mode) {
		case 0:
			x = 70;
			y = 80;

			Card first = cards.get(0);
			first.draw(g, x, y);

			break;
		case 1:

			x = 70;
			y = 330;
		
			//	Check if there are cards in cards, if not then draw a rounded rect
			if (cards.size() > 0) {
				for (int i = 0; i < cards.size(); i++) {
					Card c = cards.get(i);

					//	Set the x position of the pile depending on the pile's position on the board
					x = 70 + 100 * pileNumber;
					c.draw(g, x, y);
					y += 19;
				}
			} else if (cards.size() == 0) { 	
				g.drawRoundRect(x, y, 70, 120, 25, 50);
			}
			break;
		case 2:
			x = 180;
			y = 80;

			for (int i = 0; i < cards.size(); i++) {
				Card c = cards.get(i);
				c.draw(g, x, y);
				x += 19;
			}
			break;
		}	 
	}
	
	public String toString() {
		String s = "\n ------ Pile " + pileNumber + " " + "------ \n";
		for (Card c : cards) {
			s += c.toString();
		}
		return s;
	}
	
}
