import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.Graphics2D;
import javax.swing.KeyStroke;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BallBouncePanel extends JPanel {

	private BallBounce ballBounce;

	public BallBouncePanel(Dimension dimension, BallBounce ballBounce) {
		this.ballBounce = ballBounce;
		this.setPreferredSize(dimension);
		this.setBackground(Color.BLACK);

		this.setUpClickListener();
		this.setupMouseMotionListener();
	}	

	private void setupMouseMotionListener() {
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
    		}

    		@Override
   			public void mouseDragged(MouseEvent e) {
   				ballBounce.updateArrow(e.getX(), e.getY());
    		}
		});
	}

	private void setUpClickListener() {
		this.requestFocusInWindow();

		this.addMouseListener(new MouseListener() {

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
				ballBounce.addBall(click.getX(), click.getY());
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

				ballBounce.releaseArrow();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (Ball b : ballBounce.balls()) {
			b.draw(g2);
		}

		Arrow arrow = ballBounce.arrow();
		if (arrow != null) {
			arrow.draw(g2);
		}

	}

}