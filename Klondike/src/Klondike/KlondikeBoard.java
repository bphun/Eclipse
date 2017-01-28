import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

public class KlondikeBoard {

	//	A list of seven Piles that are drawn horizontally on the Board
	private static List<Pile> piles;

	private static Pile deck;

	private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};

	List<List<Card>> selectedCards;

	private static final String BACK_CARD_FILE_NAME = "cards/back1.GIF";

	public KlondikeBoard() {
		piles = new ArrayList<>();
		selectedCards = new ArrayList<>();

		//	Initialize the seven piles with (i - 1) cards each
		for (int i = 1; i < 8; i++) {
			piles.add(new Pile(i, i - 1));
			
			// for(Card c:piles.get(piles.size()-1).cards()){
			// 	if (c.faceDown()) {
			// 		c.flip();
			// 	}
			// }
		}

		for (Pile p : piles) {
			p.get(p.size() - 1).flip();
		}
		// Use this to test adding cards to a pile
		List<Card> cards = new ArrayList<>();
		for (int i = 0; i < SUITS.length; i++) {
			Card c = new Card(RANKS[i], SUITS[i]);
			c.flip();
			cards.add(c);
		}
		piles.get(0).addCards(cards);
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
			for (int i = p.size() - 1; i >= 0; i--) {
				Card c = p.get(i).containsPoint(x,y);
				if ((c != null) && (c.faceUp())) {
					if (selectedCards.size() < 2) {
						List<Card> selected_ = new ArrayList<>();
						for (int j = i; j < p.size(); j++) {
							p.get(j).setSelected();
							selected_.add(p.get(j));
						}

						if (!selectedCards.contains(selected_)) {
							selectedCards.add(selected_);
						} else {
							selectedCards.remove(selected_);
						}

						if (selectedCards.size() == 2) {
							transferCards();
						}
					}
				}
			}
		}
	}

	private void transferCards() {
		Pile destinationPile = null;
		Pile startPile = null;
		for (Pile p : piles ) {
			for (Card c : p.cards()) {
				if (selectedCards.get(0).contains(c)) {
					startPile = p;
				} else if (selectedCards.get(1).contains(c)) {
					destinationPile = p;
				}
			}
		}
		destinationPile.addCards(selectedCards.get(0));

		List<Card> cardsToRemove = new ArrayList<>();
		for (Card c : startPile.cards()) {
			if (c.isSelected()) {
				cardsToRemove.add(c);
			}
		}
		startPile.remove(cardsToRemove);

		startPile.cards().get(startPile.cards().size() - 1).flip();

		for (Card c : destinationPile.cards()) {
			if (c.isSelected()) {
				c.setSelected();
			}
		}

		// selectedCards.clear();
		selectedCards = new ArrayList<>();
	}

	private int numSelectedCards() {
		int i = 0;
		for (Pile p : piles) {
			for (Card c : p.cards()) {
				if (c.isSelected()) {
					i++;
				}
			}
		}
		return i;
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
