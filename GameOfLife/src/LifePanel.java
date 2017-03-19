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

	final static int SQUARE_WIDTH = 12;
	final static int LINE_THICKNESS = 1;
	final static Dimension DIMENSIONS = new Dimension(750, 500);
	final static int MIN_SPEED = 1200;
	final static int MAX_SPEED = 300;

	private JButton nextButton;
	private JButton startButton;
	private JButton clearButton;
	private JButton rewindButton;
	private JSlider playSpeed;
	private JComboBox<String> savedLayouts;
	
	private LifeAsWeKnowIt life;
	
	public LifePanel(int[][] grid, LifeAsWeKnowIt life) {
		this.grid = grid;
				// setBackground(new Color(84,110,122));
		setBackground(new Color(69,90,100));
		setPreferredSize(DIMENSIONS);

		this.life = life;
		setUpClickListener();
		addUI();
		setVisible(true);
	}

	public void refresh() {
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
		int row = e.getY() / SQUARE_WIDTH;
		int col = e.getX() / SQUARE_WIDTH;
		if (grid  == null) { return; }
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
		playSpeed = new JSlider(JSlider.VERTICAL, MIN_SPEED, MAX_SPEED);
		
		playSpeed.setInverted(true);

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
	
	public void setGrid(int[][] grid) {
		this.grid = grid;
		repaint();
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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		drawGrid(g2);
		
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == 1) {
					g.setColor(new Color(67, 160, 71));
					g2.fillRect(c * SQUARE_WIDTH + LINE_THICKNESS, r * SQUARE_WIDTH + LINE_THICKNESS, SQUARE_WIDTH - LINE_THICKNESS, SQUARE_WIDTH - LINE_THICKNESS);		
					g.setColor(Color.BLACK);
				}
			}
		}
	}

	private void drawGrid(Graphics2D g2) {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				//	Draws the vertical line
				g2.drawLine(SQUARE_WIDTH * c, 0, SQUARE_WIDTH * c, (DIMENSIONS.height) - 45);
			}
			//	Draws the horizontal line
			g2.drawLine(0, (SQUARE_WIDTH * r), DIMENSIONS.width, (SQUARE_WIDTH * r)); 
		}
	}

}
