import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class KlondikePanel extends JPanel {

	//	The preferred dimensions of the board
	private static final Dimension PREFERRED_DIM = new Dimension(800,700);
	
	private static KlondikeBoard klondikeBoard;
	
	public KlondikePanel() {
		klondikeBoard = new KlondikeBoard();

		setUpClickListener();

		setBackground(new Color(46,125,50));
//		setBackground(new Color(27,94,32));2
		this.setPreferredSize(PREFERRED_DIM);
		setVisible(true);
	}
	
	private void setUpClickListener() {
		this.requestFocusInWindow();
		this.addMouseListener(new MouseListener() {

			//	If you want to detect mouse dragging, then use a mouseMotionListener
			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}
			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent click) {
				clickedAt(click);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});

	}
	
	protected void clickedAt(MouseEvent click) {	
		klondikeBoard.clickedAt(click.getX(), click.getY(), click);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g;

		//	Turn on antialiasing so that there are no jagged lines and the lines look better
		final RenderingHints antialiasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		/*
		 * Increase rendering quality for aesthetic purposes, may need to change to VALUE_COLOR_RENDER_DEFAULT later
		 * Link with different paramters here: https://docs.oracle.com/javase/tutorial/2d/advanced/quality.html
		 */
		final RenderingHints quality = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		//	Add the rendering hints
		graphics2d.addRenderingHints(antialiasing);
		graphics2d.addRenderingHints(quality);	
		
		//	Draw the GUI
		klondikeBoard.drawBoardGUI(graphics2d);
	}

}
