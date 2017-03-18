
import java.awt.Point;
import java.awt.Rectangle;

public class Location {
	
	public int x,y;
	
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Location(Location other) {
		x = other.x;
		y = other.y;
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
}