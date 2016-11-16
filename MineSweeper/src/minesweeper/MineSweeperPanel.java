package minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class MineSweeperPanel extends JPanel {

	private static final int SQ = 16;// the width and height of a square

	Image unClickedSquare, flaggedSquare;// Images to be displayed
	
	// this contains the mines.  zero means no mine, 1 means mine
	 // using zeros and ones instead of booleans will make it easier 
	 // to add up the number of mines surrounding a square.
	// when we know the size (when this panel is constructed) we will
	// create the grid to be the correct size and then fill it randomly
	// with the correct number of mines
	int[][] mineGrid;
				
	// this will store the numbers that will be displayed when a 
	// square is clicked.  This will be created after creating the 
	// mineGrid, so that the correct numbers can be placed into it
	int[][] numbers;
	
	// This is how you make a 2D array and initialize it all in one step
	// this is a 2x3 matrix.  This first row represents the number of rows
	// for any gameboard, and the second represents the corresponding cols
	public final int[][] ROW_COL = {{10, 30, 50},
			 				 		{10, 16, 50}};
	// percentage of spaces with mines
	final double[] DIFF = {.125, .25, .6};
	// this is the sizeIndex that causes the user to input the dimensions
	final int CUSTOM_INDEX = 3;
	
	// more instance variables will be created!
	
	
	
	
	public MineSweeperPanel(int diffIndex, int sizeIndex) {
		// See below for the proper way to open images and other types of
		// resources
		openImages();
		
		// this uses the two ints below to access ROW_COL and 
		// create the 2D array of ints
		createGrid(diffIndex, sizeIndex);
		
		// base the size of this JPanel upon the dimension of the 2D array
		// which has just been initialized by the method above
		setPreferredSize(new Dimension(mineGrid[0].length*SQ,mineGrid.length*SQ));
		
		// See the method below for the BEST way to set up ways to interact with 
		// mouse events.  Similar to interact with dragging and moving mouse
		setUpClickListener();
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
				// use this method when you wish to detect a click
				System.out.println(click);
				
				// here are some things you can do with a MouseEvent
				System.out.println(click.getX());
				System.out.println(click.getPoint());
				System.out.println(click.getButton());// try clicking left and right buttons
			
				//// So... What should we do when we detect a click?
				// This is a good way to isolate the clicking and the behavior
				// to be done when the JPanel is clicked
				clickedAt(click);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// use this to find out when the mouse was released
				
			}
			
		});
		
	}


	protected void clickedAt(MouseEvent click) {
		// This is called when the panel is clicked.  What should we do?
		
	}


	private void createGrid(int diffIndex, int sizeIndex) {
		int[] row_col;
		if(sizeIndex == this.CUSTOM_INDEX) {
			row_col = createCustomSize();
		}
		else {
			row_col = new int[] {ROW_COL[0][sizeIndex], ROW_COL[1][sizeIndex]};
		}
		mineGrid = new int[row_col[0]][row_col[1]];
		System.out.println(mineGrid.length+"  by  "+mineGrid[0].length);
		
		
		
	}
	private int[] createCustomSize() {
		int rows = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows"));
		int cols = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of cols"));
		return new int[]{rows,cols};
	}


	@Override
	public void paintComponent(Graphics g) {
		/*
		 * PLEASE, PLEASE, PLEASE, when you use Java Graphics, have this method be 
		 * the "trigger" for ALL drawing to be done should begin HERE, in this method!!!!!
		 * 
		 * All code needed to redraw this panel from scratch goes here.  JPanels
		 * are double-buffered by default, so don't worry about extra things that are redrawn
		 */
		
		// you can draw shapes
		g.setColor(Color.red);
		g.drawLine(0, 30, 80, 200);
	
		// you can make your own colors
		g.setColor(new Color(68, 200, 100));
		// (x,y) of the upper left hand corner, width = 25, height = 50
		g.fillRect(200, 50, 25, 50);
		
		//This is how you draw images
		g.drawImage(this.unClickedSquare, 10,10,null);
		g.drawImage(this.flaggedSquare, 100,10,null);
	}

	private void openImages() {
		
/*
 * FOLLOW THE EXAMPLE BELOW FOR OPENING RESOURCES!!!!!!!!!!!!
 */
		try {
			URL url = getClass().getResource("res/images/unclicked.png");
			unClickedSquare = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening the unclicked.png");
			e.printStackTrace();
		}
		try {
			URL url = getClass().getResource("res/images/flagged.png");
			flaggedSquare = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening the unclicked.png");
			e.printStackTrace();
		}
	}

}
