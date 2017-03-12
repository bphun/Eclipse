package donjeweled;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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

import javafx.scene.layout.Border;



public class DonJeweledPanel extends JPanel {

	private static final Dimension PREFERRED_DIM = new Dimension(800,1200);
	
	private final static int COLS = 8, ROWS = 9;

	private static final int SQ= (int) (PREFERRED_DIM.getWidth()/COLS);
	private JProgressBar timeLeftBar;							                 

	private static final int TOP_PADDING = (int) (PREFERRED_DIM.getHeight()-ROWS*SQ);
	
	private List<Image> backGroundList = new ArrayList();
	private int TEXT_YC = 20;
	private int timeRemaining;
	private Timer timer;
	private Don[][] donGrid;
	private Don[][] prevDonGrid;
	
	private String statusStr = "";
	
	private int points = 0;
	
	public DonJeweledPanel() {
		// See below for the proper way to open images and other types of
		// resources
		System.out.println("Opening the panel!!");
		openImages();
		this.setPreferredSize(PREFERRED_DIM);
		// See the method below for the BEST way to set up ways to interact with 
		// mouse events.  Similar to interact with dragging and moving mouse
		setUpClickListener();
		setUpGame();
		setUpTimer();
		setVisible(true);
		timer.start();
	}
	Don[] dons = {new Simms(), new DrDre(), new Ryan(), new Thiel(), new Evan()};

	boolean gameOver = false;
	
	private JProgressBar progressBar;
	
	private void setUpGame() {
		// set up the grid.
		// add Dons to the Grid
		donGrid = new Don[ROWS][COLS];
		
		progressBar = new JProgressBar(0,1000);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		add(progressBar);
		
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				int rand = (int) (Math.random() * dons.length);		
				if (donGrid[r][c] == null) {
					switch(rand) {
					case 0: 
						donGrid[r][c] = dons[0];
						break;
					case 1:
						donGrid[r][c] = dons[1];
						break;
					case 2:
						donGrid[r][c] = dons[2];
						break;
					case 3:
						donGrid[r][c] = dons[3];
						break;
					case 4:
						donGrid[r][c] = dons[4];
						break;
					default:
						break;
					}	
				}					
			}
		}
		
		if (threeInRow() != null) {
			while (threeInRow() != null) {
				for (int r = 0; r < ROWS; r++) {
					for (int c = 0; c < COLS; c++) {
						int rand = (int) (Math.random() * 7);	
						switch(rand) {
						case 0: 
							donGrid[r][c] = dons[0];
							break;
						case 1:
							donGrid[r][c] = dons[1];
							break;
						case 2:
							donGrid[r][c] = dons[2];
							break;
						case 3:
							donGrid[r][c] = dons[3];
							break;
						case 4:
							donGrid[r][c] = dons[4];
							break;
						default:
							break;
						}					
					}
				}
			}
		}		
	}


	private void setUpTimer() {
		timeRemaining = 600;
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeRemaining <= 0) {
					gameOver = true;
					statusStr = "Game Over";
				}
				timeRemaining--;
				statusStr = "Time Remaining: " + timeRemaining;
				updateProgressBar();
				repaint();
			}
		});
		
	}

	// We would like to see a progress bar get smaller and smaller
	// as time elapses.
	protected void updateProgressBar() {
		progressBar.setValue(points);
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
				// I like to avoid using this one because if you are moving
				// the mouse while you are trying to click, it sometimes doesn't
				// register the click.  A click is a press and release at the 
				// same location

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// when the mouse enters the panel, this is called

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// duh...

			}

			@Override
			public void mousePressed(MouseEvent click) {
				clickedAt(click);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				clickRelease(arg0);
			}

		});

	}


	int click_X = 0;
	int click_Y = 0;
	
	int clickRelease_X = 0;
	int clickRelease_Y = 0;
	
	protected void clickedAt(MouseEvent click) {
		click_X = click.getX();
		click_Y = click.getY();		
	}

	void clickRelease(MouseEvent click) {
		clickRelease_X = click.getX();
		clickRelease_Y = click.getY();
		String direction = getDragDirection();		

		prevDonGrid = donGrid;

		if (direction == null) { return; }
				
		Don d = donGrid[click_Y / SQ][click_X / SQ];
		switch (direction) {
		case "left":
			Don left = donGrid[click_Y / SQ][(click_X / SQ) - 1];
			donGrid[click_Y / SQ][(click_X / SQ) - 1] = d;
			d = left;
			donGrid[click_Y / SQ][click_X / SQ] = left;
			break;
		case "right":
			Don right = donGrid[click_Y / SQ][(click_X / SQ) + 1];
			donGrid[click_Y / SQ][(click_X / SQ) + 1] = d;
			d = right;
			donGrid[click_Y / SQ][click_X/ SQ] = right;
			break;
		case "up":
			Don up = donGrid[(click_Y / SQ) + 1][(click_X / SQ)];
			donGrid[(click_Y / SQ) + 1][(click_X / SQ)] = d;
			d = up;
			donGrid[click_Y / SQ][click_X / SQ] = up;
			break;
		case "down":
			Don down = donGrid[(click_Y / SQ) - 1][(click_X / SQ)];
			donGrid[(click_Y / SQ) - 1][(click_X / SQ)] = d;
			d = down;
			donGrid[click_Y / SQ][click_X / SQ] = down;
			break;
		}
		
		dropDons();
	
		click_X = 0;
		click_Y = 0;
		super.revalidate();
		super.repaint();	
		
	}
	
	private int[] threeInRow() {
		
		boolean row = false;
		boolean col = false;
		
		int[] coord = new int[2];
		
		for (int r = 0; r < donGrid.length; r++) {
			for (int c = 0; c < donGrid[r].length - 2; c++) {
				if (donGrid[r][c] != null && donGrid[r][c + 1] != null && donGrid[r][c + 2] != null) { 
					if (donGrid[r][c].equals(donGrid[r][c + 1]) && donGrid[r][c].equals(donGrid[r][c + 2])) { 
						col = true; 
						coord[0] = r;
						coord[1] = c;
						break;
					}
				}
			}
		}

		if (row == true) { return coord; }
		for (int r = 0; r < donGrid.length - 2; r++) {
			for (int c = 0; c < donGrid[0].length; c++) {
				if (donGrid[r][c] != null && donGrid[r + 1][c] != null && donGrid[r + 2][c] != null) { 
					if (donGrid[r][c].equals(donGrid[r + 1][c]) && donGrid[r][c].equals(donGrid[r + 2][c])) { 
						row = true;
						coord[0] = r;
						coord[1] = c;
						break;
					}
				}
			}
		}
		
		if (row || col) {
			return coord;
		}
		
		return null;
    }
	
	private String getDragDirection() {
		final String[] directions = {"left", "right", "up", "down"};
		final int xDirection = clickRelease_X - click_X;
		final int yDirection = clickRelease_Y - click_Y;
			
//		System.out.println("X: " + xDirection + " Y: " + yDirection);
		
		if ((xDirection > 0) && (yDirection < SQ)) {
			return directions[1];
		} else if ((xDirection < 0) && (yDirection < SQ)) {
			return directions[0];
		} else if ((yDirection > 0) && (xDirection < SQ)) {
			return directions[2];
		} else if ((yDirection < 0) && (xDirection < SQ)) {
			return directions[3];
		}
		
		
		return null;
	}
	
	// this is called any time new Dons need to be added
	private void addDons() {
		// in case there are some empty spots, tell all the Dons in the 
		// grid to check to see if any empty spots below them
		dropDons();
		// add Dons to each column and have them drop.  Repeat until col is full.
		// repeat for each column		
	}

// Starting at bottom, if there are any empty spots, have those spots get filled by 
// the Dons above, if there are any non-null Dons above
	private void dropDons() {

		int[] coords = threeInRow();
		
		if (coords == null) { 
			
			if (prevDonGrid.equals(donGrid)) {
				System.out.print("true");
			}
			
			donGrid = prevDonGrid;
			super.revalidate();
			super.repaint();
			return;
		}
		
		int r = coords[0];
		int c = coords[1];
		
		int numCleared = 0;
		
		int i = 0;
		Don start = donGrid[r][c];

		if (c + 1 < COLS && donGrid[r][c + 1] != null &&  donGrid[r][c + 1].equals(start)) {
			while (c + i < COLS && donGrid[r][c + i] != null && donGrid[r][c + i].equals(start)) {
				donGrid[r][c + i] = null;
				i++;
			}
		} else if (c - 1 > COLS && donGrid[r][c - 1] != null && donGrid[r][c - 1].equals(start)) {
			while (c - i > COLS && donGrid[r][c - i] != null &&  donGrid[r][c - i].equals(start)) {
				donGrid[r][c + i] = null;
				i++;
			}
		} else if (r + 1 < ROWS && donGrid[r + 1][c] != null && donGrid[r + 1][c].equals(start)) {
			while (r + i < ROWS && donGrid[r + i][c] != null && donGrid[r + i][c].equals(start)) {
				donGrid[r + i][c] = null;
				i++;
			}
		} else if (r - 1 > ROWS && donGrid[r - 1][c] != null && donGrid[r - 1][c].equals(start)) {
			while (r - i > ROWS && donGrid[r - i][c] != null && donGrid[r - i][c].equals(start)) {
				donGrid[r - i][c] = null;
				i++;
			}
		}

		for (int row = ROWS - 1; row > 0; row--) {
			for (int col = COLS - 1; col > 0; col--) {
				if (donGrid[row][col] == null) {
					//Don bottom = donGrid[row + 1][col];
					donGrid[row][col] = donGrid[row-1][col];
					//donGrid[row][col] = bottom;
					donGrid[row-1][col] = null;
					numCleared++;
				}
			}
		}
	
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (donGrid[row][col] == null) {
					int rand = (int) (Math.random() * dons.length);
					donGrid[row][col] = dons[rand];		
				}
			}
		}
		
		points += (10 * numCleared);
		
		updateProgressBar();
		
		coords = threeInRow();
		if (coords != null) {
			dropDons();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);
				
//		if (!gameOver) {
//			g.drawString("Remaining: " + timeRemaining, 200, 20);
//		} else {
//			g.drawString("Game Over", 200, 20);
//			return;
//		}
		g.drawString(statusStr, 170, 20);
		
		for(int r = 0; r < donGrid.length; r++) {
			for(int c = 0; c < donGrid[0].length;c++) {
				Don d = donGrid[r][c];
				
				if(d != null) {
					d.draw(g,c*SQ, 30 + SQ*r,SQ, SQ);
				}
			}
		}

		drawExtraStuff(g);
	}

	private void drawExtraStuff(Graphics g) {
		// TODO Auto-generated method stub
		
	}


	private void drawBackground(Graphics g) {
		// TODO Auto-generated method stub
		
	}


	private void openImages() {
		/*
		 *Only Open Images needed in this class that are needed
		 *by this class.  Images that each Don uses will be opened 
		 *in that Don class. 
		 **/
		try {
			URL url = getClass().getResource("res/images/simms.jpg");
			backGroundList.add( ImageIO.read(url));
		} catch (IOException e) {
			System.out.println("Problem opening the simms.jpg");
			e.printStackTrace();
		}
		try {
			URL url = getClass().getResource("res/images/drDre.jpg");
			backGroundList.add( ImageIO.read(url));
		} catch (IOException e) {
			System.out.println("Problem opening the drDre.jpg");
			e.printStackTrace();
		}
		
	}

}
