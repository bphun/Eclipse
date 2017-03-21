import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LifePanel extends JPanel {

	// Contains layout of the grid (selected/unselected squares)
	private int[][] grid;

	//	Width and height of each square drawn in the panel
	final static int SQUARE_SIZE = 12;

	// Thickness of each stroke drawn to create the grid
	final static int LINE_THICKNESS = 1;

	final static Dimension PANEL_DIMENSIONS = new Dimension(750, 500);

	// Minimum speed at which the simulation will play
	final static int MIN_SPEED = 1200;

	//	Maximum speed at which te simulation plays
	final static int MAX_SPEED = 300;

	//	The UI at the bottom of the panel, used to interface with the simulation
	private JButton nextButton;
	private JButton startButton;
	private JButton clearButton;
	private JButton rewindButton;
	private JSlider playSpeed;
	private JComboBox<String> savedLayouts;

	// Contains the simulation logic
	private LifeAsWeKnowIt life;

	private int numSteps;
	
	public LifePanel(int[][] grid, LifeAsWeKnowIt life) {
		this.grid = grid;
				// setBackground(new Color(84,110,122));
		setBackground(new Color(69,90,100));
		setPreferredSize(PANEL_DIMENSIONS);

		this.numSteps = 0;

		this.life = life;
		setUpClickListener();
		addUI();
		setVisible(true);
	}

	//	Used in LifeAsWeKnow it to redraw the panel
	public void refresh() {
		repaint();
	}
	
	public void setGrid(int[][] grid) {
		this.grid = grid;
		repaint();
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

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				clicked(arg0);
			}

		});
	}

	private void clicked(MouseEvent e) {
		int row = e.getY() / SQUARE_SIZE;
		int col = e.getX() / SQUARE_SIZE;
		if (grid == null) { return; }
		if (grid[row][col] == 1) {
			grid[row][col] = 0;
		} else {
			grid[row][col] = 1;
		}
		repaint();
	}

	private void addUI() {
		nextButton = new JButton("Next");
		startButton = new JButton("Start");
		clearButton = new JButton("Clear");
		rewindButton = new JButton("Rewind");
		savedLayouts = new JComboBox<String>(life.getSavedLayoutTitles());
		playSpeed = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED);
		
		// Make the slider start at the min speed to max speed instead of max speed to min speed
		playSpeed.setInverted(true);

		// Create the table that contains the labels for Min and Max sides of the slider
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(MAX_SPEED), new JLabel("Fast"));
		labelTable.put(new Integer(MIN_SPEED), new JLabel("Slow"));
		playSpeed.setLabelTable(labelTable);

		playSpeed.setPaintLabels(true);

		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nextButtonAction();
			}
		});
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startButtonAction();
			}		
		});
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearButtonAction();
			}	
		});
		rewindButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rewindButtonAction();
			}	
		});

		savedLayouts.setSelectedIndex(3);
		savedLayouts.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxAction();
			}	
		});

		playSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				playSpeedChange();
			}
		});

		//	Create another panel sothat contains UI so that we can move it to the bottom of the panel
		this.setLayout(new BorderLayout());
		JPanel UIPanel = new JPanel();
		UIPanel.setBackground(new Color(69,90,100));
		UIPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		UIPanel.add(startButton);
		UIPanel.add(playSpeed);
		UIPanel.add(nextButton);
		UIPanel.add(clearButton);
		UIPanel.add(rewindButton);
		UIPanel.add(savedLayouts);
		this.add(UIPanel, BorderLayout.SOUTH);
	}

	private void playSpeedChange() {
		life.updatePlaySpeed(playSpeed.getValue());
	}

	private void comboBoxAction() {
		life.loadGrid((String)savedLayouts.getSelectedItem());
	}

	private void rewindButtonAction() {
		life.rewind();
	}
	
	private void clearButtonAction() {
		this.grid = new int[grid.length][grid[0].length];
		life.setGrid(this.grid);
		savedLayouts.setSelectedIndex(3);
	}

	private void nextButtonAction() {
		life.step();
	}

	boolean currentlyPlaying = false;
	private void startButtonAction() {
		if (currentlyPlaying) {
			startButton.setText("Start");
			currentlyPlaying = false;
		} else {
			startButton.setText("Stop");
			currentlyPlaying = true;
		}
		life.shouldPlay();
		life.play();
	}
	
	public void setSteps(int steps) {
		this.numSteps = steps;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawGrid(g2);
		
		// g2.drawString()

		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == 1) {
					g.setColor(new Color(67, 160, 71));
					// switch ((int)(Math.random() * 7)) {
					// 	case 0:
					// 		g2.setColor(Color.RED);
					// 		break;
					// 	case 1:
					// 		g2.setColor(Color.ORANGE);
					// 		break;
					// 	case 2:
					// 		g2.setColor(Color.YELLOW);
					// 		break;
					// 	case 3:
					// 		g2.setColor(Color.GREEN);
					// 		break;
					// 	case 4:
					// 		g2.setColor(Color.BLUE);
					// 		break;
					// 	case 5:
					// 		g2.setColor(Color.MAGENTA);
					// 		break;
					// 	case 6:
					// 		g2.setColor(new Color(216, 191, 216));
					// 		break;
					// }
					g2.fillRect(c * SQUARE_SIZE + LINE_THICKNESS, r * SQUARE_SIZE + LINE_THICKNESS, SQUARE_SIZE - LINE_THICKNESS, SQUARE_SIZE - LINE_THICKNESS);		
					g.setColor(Color.BLACK);
				}
			}
		}
	}

	private void drawGrid(Graphics2D g2) {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				//	Draws the vertical line
				g2.drawLine(SQUARE_SIZE * c, 0, SQUARE_SIZE * c, (PANEL_DIMENSIONS.height) - 45);
			}
			//	Draws the horizontal line
			g2.drawLine(0, (SQUARE_SIZE * r), PANEL_DIMENSIONS.width, (SQUARE_SIZE * r)); 
		}
	}

}
