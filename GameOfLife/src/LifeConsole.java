import java.util.Scanner;

public class LifeConsole extends LifeAsWeKnowIt {

	private LifeAsWeKnowIt life;

	public LifeConsole(int rows, int cols, LifeAsWeKnowIt life) {
		grid = new int[rows][cols];
		this.start();
		this.life = life;
	}

	private void start() {
		printGrid();
		// while (!scanner.next().equalsIgnoreCase("quit")) {
		// 	String input = scanner.next();

		// 	if (input.equalsIgnoreCase("next")) {
		// 		step();
		// 	} else if (input.equalsIgnoreCase("start")) {
		// 		shouldPlay();
		// 		play();
		// 	}
		// }
	}
	

	private void printGrid() {
		if (grid == null) { return; }
		System.out.println("Grid: ");
		for (int[] r : grid) {
			for (int c : r) {
				switch (c) {
					case 0:
						System.out.print(".");
						break;
					case 1:
						System.out.print("*");
						break;

				}
			}
			System.out.println();
		}
	}
	
}
