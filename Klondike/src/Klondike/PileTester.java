
public class PileTester {

	public static void main(String[] args) {
		Pile p = new Pile(7, 6);

		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < SUITS.length; i++) {
			cards.add(new Card(RANKS[i], SUITS[i]));
		}
		p.addCards(cards);
		System.out.println(p.size());

	}

}
