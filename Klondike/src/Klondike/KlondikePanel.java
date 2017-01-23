import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;

public class KlondikePanel extends JPanel {

	private static final Dimension PREFERRED_DIM = new Dimension(1170,900);

	private static int click_X = 0;
	private static int click_Y = 0;
	
	private static KlondikeBoard klondikeBoard;
	
	public KlondikePanel() {
		
		klondikeBoard = new KlondikeBoard();
		
		setBackground(new Color(46,125,50));
//		setBackground(new Color(27,94,32));
		this.setPreferredSize(PREFERRED_DIM);
		setVisible(true);
	}
	
	private void setUpClickListener() {
		// Whoever has focus is who can interact with mouse and keyboard, etc
		this.requestFocusInWindow();

		// similar to having an entity ready to interact with the Mouse
		this.addMouseListener(new MouseListener() {
			/*
			 * If you want to detect mouse dragging, then use a mouseMotionListener
			 */
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
		click_X = click.getX();
		click_Y = click.getY();		
		klondikeBoard.clickedAt(click_X, click_Y);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D) g;

		final RenderingHints antialiasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		final RenderingHints quality = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics2d.addRenderingHints(antialiasing);
		graphics2d.addRenderingHints(quality);	
		
		klondikeBoard.drawBoardGUI(graphics2d);
	}

}
