import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.Timer;


public class LifeAsWeKnowIt {
	
	private int displayType = 3;
	private int rows = 35;
	private int cols = 63;
	
	private int[][] grid;// this is how you declare a 2D array of int.  What if you wanted 
				 // a 2D array of String?  
	
	private JFrame frame;// to be used if displayType is not 1 or 2
	
	private LifeWorld world;// Gridworld world.  This was part of the APCS curriculum until 
	                // 2015 Exam.  It is a nice class for displaying things that are
					// typically displayed in grid fashion.
	
	
	private LifePanel panel;// this is a type of JPanel, and thus has all functionality of one.
	 				// I have provided this class for you.  It has a couple of functions
					// but you can add more, if you like.
	
	private Timer timer;
	
	private boolean shouldPlay;
	
	public static void main(String[] args) {
		new LifeAsWeKnowIt().start();
	}
	
	private void start() {
//		displayType = promptDisplay();
		
		world = new LifeWorld(rows, cols);
		
		loadLife();
		show();
		for(int steps = 0; steps < 10; steps++) {
			step();	// advances to the next generation
			show();	// displays current gen
			pause(); // pauses... may change according to the display type
		}
	}

	private int promptDisplay() {
		// TODO Auto-generated method stub
		return 1;
	}

	private void pause() {
		if(this.displayType == 1) {
			System.out.println("Hit enter to continue");
			new Scanner(System.in).nextLine();
		}
		// otherwise, use the buttons in the other interface
	}

	public void step() {
		// takes the organisms from one generation to the next, instantaneously
		
	}
	
	public void play() {
//		if (shouldPlay) {
//			while (true) {
//				step();
//				System.out.println("Start");
//			}
//		} else {
//			shouldPlay = false;
//		}
	}
	
	public void shouldPlay() {
		shouldPlay = true;
	}

	private void show() {
		if(displayType == 1) {
			dispConsole();
		} else if(this.displayType == 2) {
			displayGridWorld();
		} else {
			displayCool();
		}
	}

	private void displayCool() {
		// checks to see if the LifePanel is null.  If so, makes a new one and adds it 
		// to the Jframe, then asks the JFrame to setVisible(true)  
		// Then, asks the LifePanel to display the grid;
		if (panel == null) {
			if (grid == null) {
				grid = new int[rows][cols];
			}
			panel = new LifePanel(grid, this);
			frame = new JFrame("Life As We Know It");
			frame.add(panel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			startTimer();
		}
		panel.displayGrid(grid);
	}

	private void refresh() {
		panel.refresh();
	}

	private void startTimer() {
		if (timer != null) { return; }
		timer = new Timer(11, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		timer.start();
	}
	
	private void displayGridWorld() {
		// checks to see if the world is null, first.  If so, makes a world.  
		// simply ask the LifeWorld to display the contents of gr
		if (grid == null) { 
			grid = new int[rows][cols];
		}
		world.display(grid);
	}

	private void dispConsole() {
		// go through the 2D array, displaying the life or not
		if (grid == null) {
			grid = new int[rows][cols];
		}
		world.print(grid);
	}

	private void loadLife() {
		// inputs the file containing info about the grid.  This file contains the size
		// (rows and cols) of the grid, as well as the locations of the organisms.
		
		File f = getFile();
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch(Exception e) {
			System.out.println("Ouch!  Problem with file!! "+e);
		}
		if(scan == null)
			loadLife();
//		this.rows = scan.nextInt();
//		this.cols = scan.nextInt();
//		while(scan.hasNext()) {
//			System.out.println("Still something to scan...");
//		}
	}

	private File getFile() {
		// You can substitute other approaches of acquiring a file
		return new File("life100.txt");
	}

}
