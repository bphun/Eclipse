import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class KlondikeBoard {

	//	A list of seven Piles that are drawn horizontally on the Board
	private List<Pile> piles;

	private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};

	private Card clickedCard;

	public KlondikeBoard() {
		piles = new ArrayList<>();

		//	Initialize the seven piles with (i - 1) cards each
		for (int i = 1; i < 8; i++) {
			piles.add(new Pile(i, i - 1));
		}

		// Use this to test adding cards to a pile
		// List<Card> cards = new ArrayList<>();
		// for (int i = 0; i < SUITS.length; i++) {
		// 	cards.add(new Card(RANKS[i], SUITS[i]));
		// }
		// piles.get(0).addCards(cards);
	}

	/**
	* @param x is the X coordinate of the click
	* @param y is the Y coordinate of the click
	* Determines what card has been clicked in the board
	* and returns if there is no card at the clicked position
	* by going through each pile's cards and calculating wether 
	* or not the click was in the cards boundaries
	*/
	public void clickedAt(int x, int y, MouseEvent event) {
		for (Pile p : piles) {
			for (int i = p.size() - 1; i >= p.size() - (p.numCardsAdded()); i--) {
				final Object[] containsPoint = p.get(i).containsPoint(x,y);
				if (containsPoint != null && ((Boolean)containsPoint[0])) {
					clickedCard = (Card) containsPoint[1];
					clickedCard();
					return;
				}
			}
		}
	}

	public void clickedCard() {
		c.drawBlankImage();
	}

	/**
	* @param g Graphics2D context sent from 
	* KlondikePanel in paintComponent(Graphics g)
	* Draw all necessary GUI when called
	*/
	public void drawBoardGUI(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.setStroke(new BasicStroke(2));
		int x = 170;
		for (int i = 0; i < 5; i++) {
			g.drawRoundRect(x, 30, 90, 150, 25, 50);
			switch (i) {
			case 0:
				x += 170;
				break;
			default:
				x += 120;
				break;
			}
		}
		for (final Pile p : piles) {
			p.draw(g);
		}
	}

}
