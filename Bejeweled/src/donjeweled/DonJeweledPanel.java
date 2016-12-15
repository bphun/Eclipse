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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;



public class DonJeweledPanel extends JPanel {

	private static final Dimension PREFERRED_DIM = new Dimension(800,1200);
	
	private final static int COLS = 8, ROWS = 9;

	private static final int SQ= (int) (PREFERRED_DIM.getWidth()/COLS);
	private JProgressBar timeLeftBar;							                 

	private static final int TOP_PADDING = (int) (PREFERRED_DIM.getHeight()-ROWS*SQ);
	
	private List<Image> backGroundList = new ArrayList();
	private int TEXT_YC = 20;
	private int timeElapsed;
	private Timer timer;
	private Don[][] donGrid;
	private int[][] clickState = new int[ROWS][COLS];
	
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


	
	private void setUpGame() {
		// set up the grid.
		// add Dons to the Grid
		donGrid = new Don[ROWS][COLS];
		
		Don[] dons = {new Simms(), new DrDre(), new Ryan(), new Thiel(), new Evan()};
		
		int numImages = 0;
		while (numImages <= (ROWS * COLS)) {
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
			numImages++;
		}
//		
//		
//		int numEqual = 0;
//		Don prev = null;
//		for (int r = 0; r < ROWS; r++) {
//			for (int c = 0; c < COLS; c++) {				
//				if (prev == null) {
//					prev = donGrid[r][c];
//					for (Don d : dons) {
//						if (d != donGrid[r][c]) {
//							donGrid[r][c] = d;
//						} else {
//							continue;
//						}
//					}
//				} else {
//					if (prev.equals(donGrid[r][c])) {
//						for (Don d : dons) {
//							if (d != donGrid[r][c]) {
//								donGrid[r][c] = d;
//							} else {
//								continue;
//							}
//						}
//					}
//				}
//				prev = null;
//			}
//		}

		addDons();
	}


	private void setUpTimer() {
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeElapsed++;
				updateProgressBar();
//				repaint();
			}
		});
		
	}

	// We would like to see a progress bar get smaller and smaller
	// as time elapses.
	protected void updateProgressBar() {
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
		clickState[click_Y / SQ][click_X / SQ] = 1;
	}

	void clickRelease(MouseEvent click) {
		clickRelease_X = click.getX();
		clickRelease_Y = click.getY();
		String direction = getDragDirection();		

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
		
		int[] coord = threeInRow();

		System.out.println();
//		Simms s = new Simms();
//		System.out.println("\n");
//		for (Don[] dons: donGrid) {
//			for (Don don : dons) {
//				if (don.equals(s)) {
//					System.out.print("s");
//				} else {
//					System.out.print("d");
//				}
//			}
//			System.out.println();
//		}
	
		click_X = 0;
		click_Y = 0;
		super.revalidate();
		super.repaint();	
	}
	
	private int[] threeInRow() {
		Don prev = donGrid[clickRelease_Y / SQ][clickRelease_X / SQ];
		int[] coord = new int[2];
		int numInRow = 0;
		
		if (clickRelease_X + 3 <= COLS) {
			for (int c = clickRelease_X; c <= clickRelease_X + 3; c++) {
				if (prev == donGrid[clickRelease_Y][c]) {
					System.out.println("sadf");
					numInRow++;
				}
				if (numInRow >= 3) {
					System.out.print("asf");
					numInRow = 0;
				}
			}
		}
		
		
		return null;
	}
	
	private String getDragDirection() {
		String[] directions = {"left", "right", "up", "down"};
		int xDirection = clickRelease_X - click_X;
		int yDirection = clickRelease_Y - click_Y;
			
//		System.out.println("X: " + xDirection + " Y: " + yDirection);
		
		if ((xDirection > 0) && (yDirection < SQ)) {
			return directions[1];
		} else if ((xDirection < 0) && (yDirection < SQ)) {
			return directions[0];
		} 
		
		if ((yDirection > 0) && (xDirection < SQ)) {
			return directions[3];
		} else if ((yDirection < 0) && (xDirection < SQ)) {
			return directions[2];
		}
		return null;
	}
	
	// this is called any time new Dons need to be added
	private static void addDons() {
		// in case there are some empty spots, tell all the Dons in the 
		// grid to check to see if any empty spots below them
		dropDons();
		// add Dons to each column and have them drop.  Repeat until col is full.
		// repeat for each column		
	}

// Starting at bottom, if there are any empty spots, have those spots get filled by 
// the Dons above, if there are any non-null Dons above
	private static void dropDons() {
//		for (int r = 0; r < donGrid.length; r++) {
//			for (int c = 0; c < donGrid[0].length; c++) {
//				
//			}
//		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);
		for(int r = 0; r < donGrid.length; r++) {
			for(int c = 0; c < donGrid[0].length;c++) {
				Don d = donGrid[r][c];
				
				if(d != null) {
					d.draw(g,c*SQ,SQ*r,SQ, SQ);
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
