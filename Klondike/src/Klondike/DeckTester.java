package Klondike;

public class DeckTester {

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};

	public static void main(String[] args) {
		Deck d = new Deck(RANKS, SUITS);
		
	}

}
