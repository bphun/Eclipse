import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

public class Deck {

	private List<Card> cards;
	private int size;

	public Deck(String[] ranks, String[] suits) {
		cards = new ArrayList<>();
		for (int j = 0; j < ranks.length; j++) {
			for (String suit : suits) {
				cards.add(new Card(ranks[j], suit));
			}
		}
		size = cards.size();
		shuffle();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public Card get(int index) {
		return cards.get(index);
	}

	public Card deal() {
		if (this.isEmpty()) {
			return null;
		}
		size--;
		return cards.get(size);
	}

	/**
	 * Shuffles the deck by doing a selection then a 
	 * perfect shuffle
	 */
	public void shuffle() {
		selectionShuffle();
		perfectShuffle();
	}

	/**
	 * Performs a perfect shuffle. A perfect shuffle
	 * is executed by splitting the deck of cards in half
	 * and then interleving the two decks of cards together --
	 * resulting in a "perfectly" shuffled deck
	 */
	private void perfectShuffle() {
		Card[] shuffled = new Card[cards.size()];

		int s = 0;
		for (int c = 0; c < cards.size() / 2; c++) {
			shuffled[s] = cards.get(c);
			s += 2;
		}

		s = 1;
		for (int c = cards.size() / 2; c < cards.size(); c++) {
			shuffled[s] = cards.get(c);
			s += 2;
		}
		cards.clear();
		for (Card c : shuffled) {
			cards.add(c);
		}
	}

	/**
	 * Performs a selction. A selction shuffle is
	 * a form of a random shuffle where each card are place 
	 * in randomly selected location within the deck
	 */
	private void selectionShuffle() {
		int r = cards.size();
		for (int i = 0; i < cards.size(); i++) {
			final int rand = (int) (Math.random() * r);
			// final Card value = values[i];
			final Card value = cards.get(i);
			// values[i] = values[rand];
			cards.set(i, cards.get(rand));
			// values[rand] = value;
			cards.set(rand, value);
			r--;
		}
	}

	@Override
	public String toString() {
		String s = "\n ------Deck------\n";
		for (Card c : cards) {
			s += c.color() + " " + c.rank() + " of " + c.suit() + "\n";
		}
		return s;
	}


}
