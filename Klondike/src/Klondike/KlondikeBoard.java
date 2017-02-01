import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.awt.Font;

public class KlondikeBoard {

	//	A list of seven Piles that are drawn horizontally on the Board
	private static List<Pile> piles;

	//	The pile of cards that is used to get additional cards to play with
	private static Pile deck;

	//	The pile of cards that can be added to the playing piles
	private static Pile usablePile;

	//	The List of cards that holds all the cards in the piles at the top right
	private Pile topCardsPile;
	private int[] nextRankArray;

	//	The list containing lists of cards that where selected and ready to swap between playing piles
	private List<List<Card>> selectedCards;

	public KlondikeBoard() {
		// topCardsPileList = new int[4];
		nextRankArray = new int[]{1, 1, 1, 1};
 		piles = new ArrayList<>();
		selectedCards = new ArrayList<>();

		topCardsPile = new Pile(true);

		//	Initialize the seven piles with (i - 1) cards each
		for (int i = 1; i < 8; i++) {
			piles.add(new Pile(i, i - 1));
		}

		//	This must go here for a reason that I forgot
		deck = new Pile(false);

		for (Pile p : piles) {
			p.get(p.size() - 1).flip();
		}

		// final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
		// final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};
 		// List<Card> cards = new ArrayList<>();
		// for (int i = 0; i < SUITS.length; i++) {
		// 	Card c = new Card(RANKS[i], SUITS[i]);
		// 	c.flip();
		// 	cards.add(c);
		// }
		// piles.get(2).addCards(cards);

		// Card c = new Card("ace", "spades");
		// Card c1 = new Card("ace", "hearts");
		// Card c2 = new Card("ace", "diamonds");
		// Card c3 = new Card("ace", "clubs");
		// c.flip();
		// c1.flip();
		// c2.flip();
		// c3.flip();
		// cards.add(c);
		// cards.add(c1);
		// cards.add(c2);
		// cards.add(c3);
		// piles.get(3).addCards(cards);
	}

	/**
	* @param x is the X coordinate of the click
	* @param y is the Y coordinate of the click
	* Determines what card has been clicked in the board
	* and returns if there is no card at the clicked position
	* by going through each pile's cards and calculating wether 
	* or not the click was in the cards boundaries
	*/
	public void clickedAt(int x, int y) {
		//	Check if the deck was pressed, if so, deal a card to the usable pile
		if (clickedTopDeck(x, y)) {
			addCardToUsablePile();
			return;
		}

		//	Check if the usablePile was pressed, if so, select it and add it to the selectedCards list
		if (clickedUsablePile(x, y)) {
			moveCardFromUsablePileToSelectedPlayPile(x, y);
			return;
		}

		if (clickedTopCardPile(x,y)) {
			moveCardToSelectedPlayPile(x, y);
			return;
		}
		// noMoreMoves();
		/**
		 *	Check if a playing pile was pressed, if so, check if a another pile is selected, 
		 * then move the selected card to the second pile
		 */
		clickedPlayingPile(x, y);
	}

	private boolean clickedTopCardPile(int x, int y) {
		for (Card c : topCardsPile.cards()) {
			if (c.containsPoint(x, y) != null) {
				return true;
			}
		}
		return false;
	}

	private void moveCardToSelectedPlayPile(int x, int y) {
		for (Card c : topCardsPile.cards()) {
			if (c.containsPoint(x, y) != null) {
				if (selectedCards.size() < 2) {
					List<Card> selected_ = new ArrayList<>();
					c.setSelected();
					selected_.add(c);

					if (!selectedCards.contains(selected_)) {
						selectedCards.add(selected_);
					} else {
						selectedCards.remove(selected_);
					}

					for (List<Card> cards : selectedCards) {
						for (Card card : cards) {
							System.out.println("Card: " + card);
						}
					}

					if (canTransfer()) {
						transferCards();
					}
					return;
				}
			}
		}
	}

	/**
	 * @param x is the X coordinate of the click
	 * @param y is the Y coordinate of the click
	 * 1. Checks if an ace is clicked in any of the playing piles, if one is then it will move it to the topCardsPile and return
	 * 2. Checks if any card that is not an ace is clicked, selects it, checks if another card is 
	 * selected, and then moves the first selected card to the second selected card's pile
	 */
	private void clickedPlayingPile(int x, int y) {
		for (Pile p : piles) {
			for (int i = p.size() - 1; i >= 0; i--) {
				Card c = p.get(i).containsPoint(x,y);
				if ((c != null) && (c.faceUp())) {
					if (selectedCards.size() < 2) {
						if (eligibleForTopList(c)) {
							int index = 0;
							switch (c.suit()) {
							case "spades":
								index = 0;
								nextRankArray[index]++;	//	Increment the next rank that is required becuase we already fulfilled the last requirement
								break;
							case "hearts":
								index = 1;
								nextRankArray[index]++;
								break;
							case "diamonds":
								index = 2;
								nextRankArray[index]++;
								break;
							case "clubs":
								index = 3;
								nextRankArray[index]++;
								break;
							}
							// topCardsList[index] = c;
							topCardsPile.addCard(c);

							c.setSelected();
							removeCardsFrom(p);

							if (p.get(p.size() - 1).faceDown()) {
								p.get(p.size() - 1).flip();
							}

							resetCards();
							return;
						}

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

						if (canTransfer()) {
							transferCards();
						}
					}
				}
			}
		}
	}

	/** 
	 * @param x is the X coordinate of the click
	 * @param y is the Y coordinate of the click
	 * Moves a card from the 'usablePile' to selected 'playingPile'
	 */
	private void moveCardFromUsablePileToSelectedPlayPile(int x, int y) {
		Card c = usablePile.cards().get(usablePile.size() - 1);	//	We can only click on the last card in the 'usablePile'

		if (c.containsPoint(x, y) != null) {
			List<Card> selectedCard = new ArrayList<>();
			selectedCard.add(c);
			c.setSelected();
	
			if (!selectedCards.contains(selectedCard)) {
				selectedCards.add(selectedCard);
			} else {
				selectedCards.remove(selectedCard);
			}

			if (canTransfer()) {
				transferCards();
			}
		}
	}

	/**
	 * @param c is the card that is checked for eligibility to be added to the topList
	 * Checks the eligibility a a card to be added to the topList by first, checking its 
	 * suit to determine the cards index in topList array, we then use that index to compare
	 * the cards suit as an integer to the required suit for that index in the topList
	 * @return boolean that is tells you if the card is elligible to be added to the topList
	 */
	private boolean eligibleForTopList(Card c) {
		int index = 0;
		switch (c.suit()) {
			case "spades":
				index = 0;
				break;
			case "hearts":
				index = 1;
				break;
			case "diamonds":
				index = 2;
				break;
			case "clubs":
				index = 3;
				break;
			default:
				return false;
		}
		if (c.intRank() == nextRankArray[index]) {	//	Check if the selected card can be placed in the topDeck
			return true;
		}
		return false;
	}

	/**
	 * @param x is the X coordinate of the click
	 * @param y is the y coordiante of the click
	 * Checks if the top most card in 'usablePile' was clicked and returns true if it was, false otherwise
	 * Done by calling the usablePile's last card's containsPoint method
	 * @return a boolean that tells you if the top most card in the usablePile was clicked
	 */
	private boolean clickedUsablePile(int x, int y) {
		if ((usablePile == null) || (usablePile.noCards())) { return false; }
		if (usablePile.cards().get(usablePile.size() - 1).containsPoint(x, y) != null) {
			return true;
		}
		return false;
	}

	/**
	 * @param x is the x coordinate of the click
	 * @param y is the y coordinate of the click
	 * Checks if the deck was pressed, and returns true if it was, false otherwise
	 * Done by calling the deck's top-most card's containsPoint method
	 * @return a boolean that tells you if the topDeck was clicked
	 */
	private boolean clickedTopDeck(int x, int y) {	
		if (deck.get(0).containsPoint(x, y) != null) { return true; }
		return false;
	}

	/**
	 * Adds a card to the 'usablePile', 
	 * 1. checks if the 'usablePile' is empty, returns if it is
	 * 2. Checks if the 'usablePile' is null, if it is, then we will initialize the pile with one card.
	 * If the previous wasn't executed, we will check if the pile contains three cards(max # of cards we can have in this pile),
	 * meaning that we must clear the pile and re-deal the cards again.
	 * If any of the previosu weren't executed then we will just add another card to the pile
	 */
	private void addCardToUsablePile() {
		if ((usablePile != null) && (usablePile.empty())) { return; }

		if (usablePile == null) {
			usablePile = new Pile(1);
			if (usablePile.cards().get(usablePile.size() - 1).faceDown()) {
				usablePile.cards().get(usablePile.size() - 1).flip();
			}	
		} else if (usablePile.size() < 3) {
			usablePile.deal();
			if (usablePile.cards().get(usablePile.cards().size() - 1).faceDown()) {
				usablePile.cards().get(usablePile.cards().size() - 1).flip();
			}
		} else if (usablePile.size() == 3) {
			usablePile.returnCards();
			usablePile.deal();

			if (usablePile.cards().get(usablePile.size() - 1).faceDown()) {
				usablePile.cards().get(usablePile.size() - 1).flip();
			}	
		}
		return;
	}


	/**
	 * Transfers a card from one pile to another. This is done by determining the 'startPile' and
	 * 'destinationPile', 'startPile' is determined by looping through all of the cards in the 'piles' 
	 * array and checking if the first array in 'selectedCards' contains any of the cards, the same is done
	 * for destination pile but with the second array in 'selectedCards'. Once that is done we will 
	 */
	private void transferCards() {
		Pile destinationPile = null;
		Pile startPile = null;

		for (Pile p : piles) {
			for (Card c : p.cards()) {
				if (selectedCards.get(0).contains(c)) {
					startPile = p;
				} else if (selectedCards.get(1).contains(c)) {
					destinationPile = p;
				}
				if ((destinationPile != null) && (startPile != null)) {
					break;
				}
			}
		}

		if ((startPile == null)) {
			// If the 'startPile' or 'destinationPile' are null then we must be dealing with transfering a card from the 'usablePile'
			startPile = usablePile; 

			// //	
			// for (Pile p : piles ) {
			// 	for (Card c : p.cards()) {
			// 		if (selectedCards.get(0).contains(c)) {
			// 			destinationPile = p;
			// 		}	
			// 	}
			// }
		}

		//	Move the selected cards from 'startPile' to 'destinationPile'
		destinationPile.addCards(selectedCards.get(0));
		removeCardsFrom(startPile);


		if ((startPile.size() != 0) && (startPile.cards().get(startPile.size() - 1).faceDown())) {
			startPile.cards().get(startPile.size() - 1).flip();
		}

		resetCards();
	}

	/**
	 * Checks if the user won the game. 
	 * Checks if all the cards in the topCardsPile are kings, meaning that the game was won (I think)
	 * @return a boolean that tells you if the user won the game, True if user won, False if user lost
	 */
	private boolean wonGame() {
		if (topCardsPile.noCards()) { return false; }
		int numKing = 0;

		for (Card c : topCardsPile.cards()) {
			if (c == null) { return false; }	//	We shouldn't continue because this means that we are already missing on king
			if (c.rank().equals("king")) {
				numKing++;
			}
		}
		if (numKing == 4) { return true; }
		return false;
	}

	private boolean noMoreMoves() {
		List<Card> allCards = new ArrayList<>();

		for (Pile p : piles) {
			for (Card c : p.cards()) {
				allCards.add(c);
			}
		} 
		// allCards.addAll(deck.cards());
		// allCards.addAll(usablePile.cards());
		// allCards.addAll(topCardsPile.cards());
		for (Card c : deck.cards()) {
			if (c == null) { continue; }
			allCards.add(c);
		}
		for (Card c : usablePile.cards()) {
			if (c == null) { continue; }
			allCards.add(c);
		} 
		for (Card c : topCardsPile.cards()) {
			if (c == null) { continue; }
			allCards.add(c);
		}
		Collections.sort(allCards, new CardCompare());
		for (Card c : allCards) {
			System.out.println(c.toString());
		}
		return false;
	}

	/**
	 * @param p is the pile that you want to remove selected cards from
	 * Loops through the pile's cards and removes them if they are selected cards
	 */
	private void removeCardsFrom(Pile p) {
		List<Card> cardsToRemove = new ArrayList<>();
		for (Card c : p.cards()) {
			if (c.isSelected()) { 
				cardsToRemove.add(c);
			}
		}
		p.remove(cardsToRemove);
	}

	/**
	 * Determines if a card can be transfered from on pile to another.
	 * A card can be tranferred if its rank as an integer is one less than the other card and if it is of opposite color
	 * @return a boolean that tells you if a card can be tranferred, True if the card can be tranferred, False if it can't
	 */
	private boolean canTransfer() {
		if (selectedCards.size() != 2) { return false; }
		Card firstCard = selectedCards.get(0).get(0);
		Card secondCard = selectedCards.get(1).get(selectedCards.get(1).size() - 1);
		if ((firstCard.intRank() + 1 == secondCard.intRank()) && (firstCard.color() != secondCard.color()))  {
			return true;
		} else {
			resetCards();
			return false;
		} 
	}

	/**
	 * Deselects all of the cards in the piles 
	 */ 
	private void resetCards() {
		for (List<Card> piles : selectedCards) {
			for (Card card : piles) {
				card.setSelected();
			}
		}
		selectedCards.clear();
	}

	/**
	* @param g Graphics2D context sent from 
	* KlondikePanel in paintComponent(Graphics g)
	* Draw all necessary GUI when called
	*/
	public void drawBoardGUI(Graphics2D g) {
		if (wonGame()) {
			Font font = new Font("Avenir Next", 1, 20);
			g.setFont(font);
			g.drawString("You Won!", 400, 350);
			return;
		}

		int x = 183;
		for (int i = 0; i < 5; i++) {
			g.drawRoundRect(x, 75, 71, 110, 25, 50);
			switch (i) {
			case 0:
				x += 170;
				break;
			default:
				x += 120;
				break;
			}
		}

		for (Pile p : piles) { 
			p.draw(g, 1);
		}

		deck.draw(g, 0);

		if (usablePile != null) {
			usablePile.draw(g, 2);
		}

		if (topCardsPile.noCards()) { return; }
		final int y = 70;
		for (Card c : topCardsPile.cards()) {
			if (c != null) {
				switch (c.suit()) {
				case "spades":
					x = 350;
					break;
				case "hearts":
					x = 470;
					break;
				case "diamonds":
					x = 590;
					break;
				case "clubs":
					x = 710;
					break;
				}
				c.draw(g, x, y);
				// c.setSelected();
			}
		}
	}
}
