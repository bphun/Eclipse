import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.HashSet;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingPanel extends JPanel {
	
	private HashSet<Point> points;

	private Color currentColor;
	private Drawing drawing;

	private JSlider radiusSlider;
	private JButton clearButton;
	private JRadioButton redButton;
	private JRadioButton greenButton;
	private JRadioButton blueButton;
	private JRadioButton blackButton;

	private static final Dimension PANEL_DIMENSIONS = new Dimension(900, 900);
	private static final int MIN_RADIUS = 5;
	private static final int MAX_RADIUS = 80;
	private static final Color RED = new Color(229, 57, 53);
	private static final Color GREEN = new Color(76, 175, 80);
	private static final Color BLUE = new Color(21, 101, 192);
	private static final Color BLACK = Color.BLACK;

	public DrawingPanel(Drawing drawing) {
		this.points = new HashSet<>();
		this.drawing = drawing;
		this.setPreferredSize(PANEL_DIMENSIONS);

		this.addUI();
		this.setupMouseMotionListener();
		this.setUpClickListener();
	}

	private void setupMouseMotionListener() {
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
    		}

    		@Override
   			public void mouseDragged(MouseEvent e) {
   				addPoint(e.getX(), e.getY());
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
				addPoint(click.getX(), click.getY());	
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}

		});
	}

	private void addUI() {
		this.radiusSlider = new JSlider(MIN_RADIUS, MAX_RADIUS);
		this.add(radiusSlider);
		this.clearButton = new JButton("Clear");

		this.redButton = new JRadioButton("Red");
		this.greenButton = new JRadioButton("Green");
		this.blueButton = new JRadioButton("Blue");
		this.blackButton = new JRadioButton("Black");

		blackButton.setSelected(true);

		this.clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				points.clear();
				radiusSlider.setValue((int)Math.abs(MIN_RADIUS - MAX_RADIUS) / 2);
			}	
		});

		redButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor = RED;
				greenButton.setSelected(false);
				blueButton.setSelected(false);
				blackButton.setSelected(false);
			}	
		});
		greenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor = GREEN;
				redButton.setSelected(false);
				blueButton.setSelected(false);
				blackButton.setSelected(false);
			}	
		});
		blueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor = BLUE;
				greenButton.setSelected(false);
				redButton.setSelected(false);
				blackButton.setSelected(false);
			}	
		});
		blackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentColor = BLACK;
				redButton.setSelected(false);
				greenButton.setSelected(false);
				blueButton.setSelected(false);
			}	
		});
		this.add(redButton);
		this.add(greenButton);
		this.add(blueButton);
		this.add(blackButton);
		this.add(clearButton);
	}

	private void addPoint(int x, int y) {
		this.points.add(new Point(x, y, radiusSlider.getValue(), currentColor));
	}

	public void refresh() {
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (Point p : points) {
			p.draw(g2);
		}
	}

}
