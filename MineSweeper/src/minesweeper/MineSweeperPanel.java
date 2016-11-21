package minesweeper;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MineSweeperPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	//	Images to be displayed
	private Image unClickedSquare, flaggedSquare, bombImage;

	//	The desired size of the tiles
	private static final int SQ = 50;
	
	//	The horizontal spacing between each of the tiles
	private static final int H_BUFFER = 10;

	private static int[][] clickState;
	
	/**
	 * This contains the mines.  zero means no mine, 1 means mine
	 * using zeros and ones instead of booleans will make it easier 
	 * to add up the number of mines surrounding a square.
	 * when we know the size (when this panel is constructed) we will
	 * create the grid to be the correct size and then fill it randomly
	 * with the correct number of mines 
	 */
	private int[][] mineGrid;
	
	/**
	 * This will store the numbers that will be displayed when a 
	 * square is clicked.  This will be created after creating the 
	 * mineGrid, so that the correct numbers can be placed into it
	 */
	private int[][] numbers;

	/**
 	 * Stores the (X,Y) coordinate of the location pressed on the screen,
	 * so the coordinating tile can be updated with the respected image
	 */
	private int[] repaintLocation = new int[2];

	/**
	 * This is used to determine if it is the first time the
	 * tiles are being drawn, if so then only blank tiles will be drawn
	 */
	private static boolean initialPaint = true;

	//	MARK: Constants to help determine of size and difficulty of the game
	
	/**
	 * This is how you make a 2D array and initialize it all in one step
	 * this is a 2x3 matrix.  This first row represents the number of rows
	 * for any gameboard, and the second represents the corresponding cols
	 */
	public final int[][] ROW_COL = {
			{10, 30, 50},
			{10, 16, 50}};

	//	Percentage of spaces with mines
	private final double[] DIFF = {.125, .25, .6};
	
	//	This is the sizeIndex that causes the user to input the dimensions
	private final int CUSTOM_INDEX = 3;

	public MineSweeperPanel(int diffIndex, int sizeIndex) {
		/**
		 * See below for the proper way to open images and other types of
		 * resources
		 */
		openImages();
		
		//	Rescale the images to the desired size as determined by the variable: SQ
		this.unClickedSquare = this.unClickedSquare.getScaledInstance(SQ, SQ, Image.SCALE_DEFAULT);
		this.flaggedSquare = this.flaggedSquare.getScaledInstance(SQ, SQ, Image.SCALE_DEFAULT);
		this.bombImage = this.bombImage.getScaledInstance(SQ, SQ, Image.SCALE_DEFAULT);
		/**
		 * This uses the two integers below to access ROW_COL and
		 * create the 2D array of integers 
		 */
		createGrid(diffIndex, sizeIndex);

		/**
		 * Base the size of this JPanel upon the dimension of the 2D array
		 * which has just been initialized by the method above
		 */
		setPreferredSize(new Dimension(mineGrid[0].length * SQ + 20,mineGrid.length * SQ + 20));

		/**
		 * See the method below for the BEST way to set up ways to interact with 
		 * mouse events. Similar to interact with dragging and moving mouse
		 */
		setUpClickListener();
	}


	/**
	 * This is used to track user input on the mouse
	 * and respond accordingly 
	 */
	private void setUpClickListener() {
		//	Whoever has focus is who can interact with mouse and keyboard, etc
		this.requestFocusInWindow();

		//	Similar to having an entity ready to interact with the Mouse
		this.addMouseListener(new MouseListener() {
			
			 //	If you want to detect mouse dragging, then use a mouseMotionListener
			@Override
			public void mouseClicked(MouseEvent arg0) {

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
				// use this to find out when the mouse was released

			}
		});

	}

	/**
	 * This method is used to determine what tile has been clicked
	 * and then update the tile with the appropriate image
	 */
	protected void clickedAt(MouseEvent click) {
		// This is called when the panel is clicked.  What should we do?
		final int X = click.getX();
		final int Y = click.getY();

		final int ROW = (Y / SQ);
		final int COL = (X / SQ);
		final int CLICKED_BUTTON = click.getButton();

		if (CLICKED_BUTTON == 3) {
			rightClick(COL, ROW);
		} else if (CLICKED_BUTTON == 1) {
			leftClick(COL, ROW);
		}

		//		System.out.println("Row: " + row + " Col: " + col);
	}

	/**
	 * This method is used to initialize both the mineGrid and numbers
	 * arrays with the appropriate number of elements which is defined by the 
	 * dimensions that user set in the menu panel
	 */
	private void createGrid(int diffIndex, int sizeIndex) {
		int[] row_col;
		if(sizeIndex == this.CUSTOM_INDEX) {
			row_col = createCustomSize();
		} else {
			row_col = new int[] {ROW_COL[0][sizeIndex], ROW_COL[1][sizeIndex]};
		}
		mineGrid = new int[row_col[0]][row_col[1]];
		numbers = new int[row_col[0]][row_col[1]];
		clickState = new int[row_col[0]][row_col[1]];

		System.out.println("Created a " + mineGrid.length + " by " + mineGrid[0].length + " grid");

		populateGrid((int) ((mineGrid[0].length * mineGrid.length) * DIFF[diffIndex]));
	}

	/**
	 * This method is used to randomly place bombs around the grid
	 * and then populate the numbers array with the appropriate count
	 * around each bomb
	 */
	private void populateGrid(int num) {
		int currentNum = 0;
		while (currentNum < num) {
			double row = Math.random() * mineGrid.length;
			double col = Math.random() * mineGrid[(int)row].length;

			mineGrid[(int)row][(int)col] = 1;
			currentNum++;
		}

		for (int r = 0; r < mineGrid.length - 1; r++) {
			for (int c = 0; c < mineGrid[0].length - 1; c++) {
				if (mineGrid[r][c] == 1) {
					numbers[r][c + 1]++;
					numbers[r + 1][c]++;
					numbers[r + 1][c + 1]++;
					if (r >= 1) {
						numbers[r - 1][c]++;		
					}
				}
			}
		}
		for (int r = 0; r < clickState.length; r++) {
			for (int c = 0; c < mineGrid[0].length; c++) {
				clickState[r][c] = -1;
			}
		}
		printGrid("bomb");
		printGrid("state");
	}

	/**
	 * This method is used to print out the mineGrid and numbers
	 * grid in the console for testing purposes
	 * @param grid the grid that you want to print out, bomb prints
	 * mineGrid, number prints numbers, state prints clickState, 
	 * anything else prints all grids
	 */
	private void printGrid(String grid) {
		switch (grid) {
		case "bomb":
			System.out.println("\n-------- Grid --------");
			for (int[] r : mineGrid) {
				for (int c : r) {
					System.out.print(c + " ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
			break;
		case "number":
			System.out.println("\n-------- Numbers --------");
			for (int[] r : numbers) {
				for (int c : r) {
					System.out.print(c + " ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
			break;
		case "state":
			System.out.println("\n-------- Click State --------");
			for (int[] r : clickState) {
				for (int c : r) {
					System.out.print(c + " ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
			break;
		default:
			System.out.println("\n-------- Grid --------");
			for (int[] r : mineGrid) {
				for (int c : r) {
					System.out.print(c + " ");
				}
				System.out.print("\n");
			}
			System.out.println("\n-------- Numbers --------");
			for (int[] r : numbers) {
				for (int c : r) {
					System.out.print(c + " ");
				}
				System.out.print("\n");
			}
			System.out.println("\n-------- Click State --------");
			for (int[] r : clickState) {
				for (int c : r) {
					System.out.print(c + " ");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}

	/**
	 * This method is used to handle right clicks on the mouse
	 * and updates the tiles with the appropriate image
	 */
	private void rightClick(int row, int col) {
		clickState[row][col] = 0;
		super.revalidate();
		super.repaint();
	}

	/**
	 * This method is used to handle left clicks on the mouse
	 * and updates the tiles with the appropriate image
	 */
	private void leftClick(int row, int col) {
		clickState[row][col] = 2;
//		super.revalidate();
		printGrid("number");
		super.revalidate();
		super.repaint();
	}

	/**
	 * This method is used to create a grid of a custom size 
	 * that the user defined earlier on the setup panel
	 */
	private int[] createCustomSize() {
		int rows = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows"));
		int cols = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of cols"));
		return new int[]{rows,cols};
	}

	/**
	 * This method is used to initialize the board with blank
	 * tiles when the game is loaded and used to refresh the board
	 * when a left or right click is detected on the user's mouse
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
		for (int r = 0; r < mineGrid.length; r++) {
			for (int c = 0; c < mineGrid[r].length; c++) {
				if (clickState[r][c] == -1) {
					g.drawImage(this.unClickedSquare,H_BUFFER + (SQ * r), H_BUFFER + (SQ * c),null);					
				} else if (clickState[r][c] == 0) {
					g.drawImage(this.flaggedSquare, H_BUFFER + (SQ * r), H_BUFFER + (SQ * c), null);
				} else if (clickState[r][c] == 2) {
					if (mineGrid[r][c] == 0 && numbers[r][c] != 0) {
						System.out.print("Clicked row " + r + " and colomn " + c + ", the number value was " + String.valueOf(numbers[r][c]) + "\n");
						g.drawString(String.valueOf(numbers[r][c]), (H_BUFFER * 3) + (SQ * r), (H_BUFFER * 4) + (SQ * c));	
					}
					if (mineGrid[r][c] == 1) {
						g.drawImage(this.bombImage, H_BUFFER + (SQ * r), H_BUFFER + (SQ * c), null);
					}
				}
			}
//			g2.draw(new Line2D.Double(0 + H_BUFFER, (SQ + H_BUFFER) * (r + H_BUFFER), H_BUFFER + (SQ * r), (SQ + H_BUFFER) * (r + H_BUFFER)));
		}	
	}

	/**
	 * This method is used to load all necessary image assets for the game
	 * to take place, this includes the bomb, flagged, and unclicked tile
	 */
	private void openImages() {
		try {
			URL url = getClass().getResource("res/images/unclicked.png");
			unClickedSquare = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening unclicked.png");
			e.printStackTrace();
		}
		try {
			URL url = getClass().getResource("res/images/flagged.png");
			flaggedSquare = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening flagged.png");
			e.printStackTrace();
		}
		try {
			URL url = getClass().getResource("res/images/bomb.png");
			bombImage = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening bomb.png");
			e.printStackTrace();
		}
	}

}



