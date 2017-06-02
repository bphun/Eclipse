import java.awt.Color;
import java.awt.Graphics2D;

public class Point {
	
	private	int x, y, radius;
	private Color color;

	public Point(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}

	public void draw(Graphics2D g2) {
		g2.setColor(this.color);
		g2.fillOval(this.x, this.y, this.radius, this.radius);
		g2.setColor(Color.BLACK);
	}


}