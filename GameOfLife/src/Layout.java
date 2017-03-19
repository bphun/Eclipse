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
		this.locations.add(location);
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
			grid[l.y()][l.x()] = 1;
		}
		return grid;
	}

	@Override
	public String toString() {
		String str = "Name: " + this.name + ", Locations: \n";
		if (locations.isEmpty()) {
			return str + "Locations is empty";
		}
		for (Location l : locations) {
			str += l.toString() + "\n";
		}
		return str;
	}

}
