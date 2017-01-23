package Klondike;
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

	public void shuffle() {
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

	@Override
	public String toString() {
		String s = "\n ------Deck------\n";
		for (Card c : cards) {
			s += c.color() + " " + c.rank() + " of " + c.suit() + "\n";
		}
		return s;
	}


}
