import java.util.List;
import java.util.ArrayList;

public class Layout {
	
	private String name;
	private List<Location> locations;
	
	public Layout(String name, List<Location> locations) {
		this.name = name;
		this.locations = locations;
	}

	public Layout() {
		name = "";
		locations = new ArrayList<>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addLocation(Location location) {
		locations.add(location);
	}
	
	public String name() {
		return this.name;
	}

	public List<Location> locations() {
		return locations;
	}

	public boolean unnamed() {
		return name == "" || name == " ";
	}

	public int[][] grid(int rows, int cols) {
		int[][] grid = new int[rows][cols];
		for (Location l : locations) {
			if (l.x() > cols || l.y() > rows) { continue; }
			grid[l.y() - 1][l.x() - 1] = 1;
		}
		for (int[] r : grid) {
			for (int c : r) {
				System.out.print(c);
			}
			System.out.println();
		}
		return grid;
	}

}
