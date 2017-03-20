import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.HashMap;

public class LifeAsWeKnowIt {

	private static final int ROWS = 40;
	private static final int COLS = 63;
	private static final String SAVE_FILE_DIRECTORY = "../SaveFile.txt";

	private int displayType = 1;
	private int numRewinds = 2;

	// Contains layout of the grid (selected/unselected squares)
	protected int[][] grid;

	//	Contains the number of neighbors each cell has
	protected int[][] neighbors;
	
	//	The past grids that have been created, used to rewind to past generations
	protected HashMap<Integer, TwoDimensionArray> history;
	
	private HashMap<String, Layout> savedLayouts;

	private JFrame frame;
	private LifePanel panel;

	private LifeConsole console;

	//	The timer that is used for the game loop which redraws the panel every 1ms
	private Timer timer;
	
	//	The timer that is used to step the game every 500ms when the start button is pressed
	private Timer playTimer;
	//	Tells the game if it should play
	private boolean shouldPlay;

	public static void main(String[] args) {
		System.setProperty("sun.java2d.opengl", "true");
		new LifeAsWeKnowIt().start();
	}

	private void start() {
		//		displayType = promptDisplay();

		neighbors = new int[ROWS][COLS];
		history = new HashMap<>();
		grid = new int[ROWS][COLS];
		history.put(new Integer(history.size()), new TwoDimensionArray(grid));
		show();
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

		history.put(new Integer(history.size()), new TwoDimensionArray(grid));

		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				switch(grid[r][c]) {
					case 1:
						if (neighbors[r][c] == 1 || neighbors[r][c] == 0) {
							grid[r][c] = 0;
						} else if (neighbors[r][c] >= 4) {
							grid[r][c] = 0;
						}
						break;
					default:
						if (neighbors[r][c] == 3) {
							grid[r][c] = 1;
						}
						break;
				}
			}
		}
	}

	protected int getNumNeighbors(int row, int col) {
		int neighbors = 0;    
		if(row != 0 && row != ROWS - 1 && col != 0 && col != COLS - 1) {
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
			// for (int r = row - 1; r <= row + 1; r++) {
			// 	for (int c = row - 1; r <= col + 1; c++) {
			// 		if (r >= ROWS || c >= COLS) { continue; }
			// 		if (grid[r][c] == 1) {
			// 			neighbors++;
			// 		}
			// 	}
			// }
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
				break;
			case 2:
				displayGridWorld();
				break;
			case 3:
				displayCool();
				break;
		}
	}

	private void displayCool() {
		if (panel == null) {
			panel = new LifePanel(grid, this);
			frame = new JFrame("Life As We Know It");
			frame.add(panel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			startPlayTimer();
			startTimer();
		}
	}

	private void startPlayTimer() {
		playTimer = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play();
			}
		});
		playTimer.start();
	}

	private void refresh() {
		panel.refresh();
	}

	public void rewind() {
		if (history.size() - numRewinds < 0) { return; }
		int[][] newGrid = history.get(new Integer(history.size() - numRewinds)).array();
		System.out.println(history.size() - numRewinds);

		for (int r = 0; r < ROWS; r++) {
			System.arraycopy(newGrid[r], 0, grid[r], 0, ROWS);
		}
		numRewinds++;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public void updatePlaySpeed(int newInterval) {
		// playTimer.stop();
		playTimer.setDelay(newInterval);
		// playTimer.start();
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
		
	}

	private void dispConsole() {
		if (console == null) {
			console = new LifeConsole(ROWS, COLS, this);
		}
	}

	public String[] getSavedLayoutTitles() {
		if (this.savedLayouts  == null) {
			this.savedLayouts = new HashMap<>();
			List<Layout> savedLayouts = new ArrayList<>();
			Layout layout = new Layout();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(new File(SAVE_FILE_DIRECTORY)));	
				for (String x = reader.readLine(); x != null; x = reader.readLine()) {
					if (x.contains("name: ")) {
						layout.setName(x.substring(6, x.length()));
						continue;
					} else if (x.contains("|")) {
						savedLayouts.add(layout);
						layout = new Layout();
						continue;
					}
					String[] coords = x.split(" ");
					layout.addLocation(new Location(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]))); 				
				}
				reader.close();
			} catch (IOException e) {
				System.out.println("Error reading (" + SAVE_FILE_DIRECTORY + ")");
				e.printStackTrace();
			}

			for (Layout l : savedLayouts) {
				this.savedLayouts.put(l.name(), l);
			}
			this.savedLayouts.put(new String("Empty"), new Layout("Empty"));

		}

		String[] fileNames = new String[savedLayouts.size()];
		int i = 0;
		for (String name : this.savedLayouts.keySet()) {
			fileNames[i] = name;
			i++;
		}
		return fileNames;
	}
	
	public void loadGrid(String key) {
		int[][] newGrid = savedLayouts.get(key).grid(ROWS, COLS);
		for (int r = 0; r < ROWS; r++) {
			System.arraycopy(newGrid[r], 0, grid[r], 0, ROWS);
		}
		panel.setGrid(this.grid);
	}

}
