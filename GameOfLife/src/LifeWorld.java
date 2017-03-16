import info.gridworld.grid.BoundedGrid;
import info.gridworld.world.World;

public class LifeWorld extends World {

	public LifeWorld(int rows, int cols) {
		super(new BoundedGrid(rows, cols));
	}
	
	public void display(int[][] gr) {
		// traverses the 2D array of ints and adds  things into the world corresponding
		// to the contents of gr.  When you add things to the world, they will show up
		// on the screen

	}
	
	public void print(int[][] gr) {
		for (int[] r : gr) {
			for (int c : r) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
}
