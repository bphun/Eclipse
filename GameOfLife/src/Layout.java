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

}
