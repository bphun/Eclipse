import java.util.Comparator;

public class CardCompare implements Comparator<Card> {

	@Override
	public int compare(Card c1, Card c2) {
		Integer c1Rank = (Integer) c1.intRank();
		Integer c2Rank = (Integer) c2.intRank();

		return c1Rank.compareTo(c2Rank);
	}
}