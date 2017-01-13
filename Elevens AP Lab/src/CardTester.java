/**
 * This is a class that tests the Card class.
 */
public class CardTester {

	/**
	 * The main method in this class checks the Card operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		Card c = new Card("ace", "Spades",0);
		Card c1 = new Card("ace", "hearts", 1);
		Card c2 = new Card("ace", "hearts", 1);
		Card c3 = new Card("ace", "hearts", 2);
		Card c4 = new Card("ace", "spades", 1);
		Card c5 = new Card("king", "hearts", 1);
		Card c6 = new Card("queen", "clubs", 3);
		
		assert c.rank().equals("ace") : "Wrong rank: " + c.rank();
		assert c.suit().equals("Spades") : "Wrong suit: " + c.suit();
		assert c.pointValue() == 0 : "Wrong point value: " + c.pointValue();
		
	
	}
}
