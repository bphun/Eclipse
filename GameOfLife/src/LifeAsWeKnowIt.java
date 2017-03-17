import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.Timer;


public class LifeAsWeKnowIt {

	private int displayType = 3;
	private int rows = 40;
	private int cols = 63;
	
	// Contains layout of the grid (selected/unselected squares)
	private int[][] grid;

	//	Contains the number of neighbors each cell has
	private int[][] neighbors;
	
	//	The past grids that have been created, used to rewind to past generations
	private List<TwoDimensionArray> history;
	
	private JFrame frame;
	private LifePanel panel;

	// private LifeWorld world;

	//	The timer that is used for the game loop which redraws the panel every 1ms
	private Timer timer;
	
	//	The timer that is used to step the game every 500ms when the start button is pressed
	private Timer playTimer;
	//	Tells the game if it should play
	private boolean shouldPlay;

	public static void main(String[] args) {
		new LifeAsWeKnowIt().start();
	}

	private void start() {
		//		displayType = promptDisplay();

		//		world = new LifeWorld(rows, cols);
		neighbors = new int[rows][cols];
		history = new ArrayList<>();
		grid = new int[rows][cols];

		loadLife();
		show();
		playTimer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		playTimer.start();
	}

	private int promptDisplay() {
		// TODO Auto-generated method stub
		return 1;
	}

	public void step() {
		for (int r = 0; r < neighbors.length - 1; r++) {
			for (int c = 0; c < neighbors[r].length - 1; c++) {
				neighbors[r][c] = getNumNeighbors(r,c);
			}
		}
		
		for (int r = 0; r < grid.length - 1; r++) {
			for (int c = 0; c < grid[r].length - 1; c++) {
				if (grid[r][c] == 1) {
					if (neighbors[r][c] == 1 || neighbors[r][c] == 0) {
						grid[r][c] = 0;
					} else if (neighbors[r][c] >= 4) {
						grid[r][c] = 0;
					} /*else if (neighbors[r][c] == 2 || neighbors[r][c] == 3) {
						continue;
					}
					*/
				} else {
					if (neighbors[r][c] == 3) {
						grid[r][c] = 1;
					}
				}
				
			}
		}
		history.add(new TwoDimensionArray(grid));
	}

	private int getNumNeighbors(int row, int col) {
		int neighbors = 0;    
		if(row != 0 && row != rows - 1 && col != 0 && col != cols - 1) {
			if(grid[row+1][col] == 1) {
				neighbors++;
			}
			if(grid[row-1][col] == 1) {
				neighbors++;
			}
			if(grid[row][col + 1] == 1) {
				neighbors++;
			}
			if(grid[row][col-1] == 1) {
				neighbors++;
			}
			if(grid[row+1][col+1] == 1) {
				neighbors++;
			}
			if(grid[row-1][col-1] == 1) {
				neighbors++;
			}
			if(grid[row-1][col+1] == 1) {
				neighbors++;
			}
			if(grid[row+1][col-1] == 1) {
				neighbors++;
			}
		}
		return neighbors;
	}

	public void play() {
		if (shouldPlay) {
			step();
		}
	}
	
	public void shouldPlay() {
		shouldPlay = !shouldPlay;
	}
	
	private void show() {
		switch (displayType) {
			case 1:
				dispConsole();
			case 2:
				displayGridWorld();
			case 3:
				displayCool();
		}
		// if(displayType == 1) {
		// 	dispConsole();
		// } else if(this.displayType == 2) {
		// 	displayGridWorld();
		// } else {
		// 	displayCool();
		// }
	}

	private void displayCool() {
		if (panel == null) {
			panel = new LifePanel(grid, this);
			frame = new JFrame("Life As We Know It");
			frame.add(panel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			startTimer();
		}
		// panel.displayGrid(grid);
	}

	private void refresh() {
		panel.refresh();
	}

	private int currentGridVersion = -1;
	public void rewind() {
//		if (currentGridVersion == -1) {
//			currentGridVersion = history.size() - 1;
//		}
//		if (currentGridVersion >= 0) {
//			int[][] temp = history.get(currentGridVersion).array();
//			history.remove(currentGridVersion);
//			for (int r = 0; r < grid.length; r++) {
//				for (int c = 0; c < grid.length; c++) {
//					grid[r][c] = temp[r][c];
//				}
//			}
//			panel.setGrid(grid);
//			currentGridVersion--;
//		}	
		
		if (currentGridVersion == -1) {
			currentGridVersion = history.size() - 1; 
		} 
		if (currentGridVersion >= 0) {
			int[][] temp = history.get(currentGridVersion).array();
			 for (int r = 0; r < grid.length; r++) {
				 for (int c = 0; c < grid[r].length; c++) {
					 grid[r][c] = temp[r][c];
					 System.out.print(grid[r][c] + ", ");
				 }
				 System.out.println();
			 }
			 panel.setGrid(grid);
		}
		
	}
		
	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	private void startTimer() {
		timer = new Timer(1, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		timer.start();
	}

	private void displayGridWorld() {
//		world.display(grid);
	}

	private void dispConsole() {
//		world.print(grid);
	}

	private void loadLife() {
		File f = getFile();
		Scanner scan = null;
		try {
			scan = new Scanner(f);
		} catch(Exception e) {
			System.out.println("Ouch!  Problem with file!! "+e);
		}
		if(scan == null)
			loadLife();

	}

	private File getFile() {
		return new File("life100.txt");
	}

}