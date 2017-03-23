import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * This class will be the display and will get the starting position
 * of the knight using a mousePress.  It should also have the data
 * like the 2D array and the current location of the knight.  The 
 * paintComponent method should redraw the view from the beginning, as it
 * always should.
 */

public class KnightsTourPanel extends JPanel {

	// what private data is needed?


	public KnightsTourPanel(int w, int h) {
		this.setPreferredSize(new Dimension(w,h));
		this.setBackground(Color.green);
		addMouseListener();
	}

	// add the mouse listener.  This will only work for the 
	// first click, and then after the first click, there should
	// be no more mouse listening!
	private void addMouseListener() {
		
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// stuff to draw the board and knight

	}
	/* make random move just selects a new location at random
	 * if the knight is trapped (no new locations to move to)
	 * then false is returned.  Otherwise, true is returned.
	 * The knight's location should be updated and the 
	 */
	public boolean makeRandomMove() {

		return false;
	}
	/* make a move to a new location that ensures the best chance
	 * for a complete traversal of the board.
	 * if the knight is trapped (no new locations to move to)
	 * then false is returned.  Otherwise, true is returned.
	 */
	public boolean makeThoughtfulMove() {

		return false;
	}
	
	
}
