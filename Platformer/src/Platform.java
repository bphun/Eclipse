import java.awt.Graphics2D;

public class Platform extends GameObject {

	Type type;

	private boolean isFloor;

	public Platform(Location location, Type type, PlatformerMap map) {
		super(location, type.width, type.height, map);
		this.type = type;
	}

	public Platform(Location location, double width, double height, PlatformerMap map) {
		super(location, width, height, map);
		isFloor = true;
	}

	public boolean isFloor() {
		return isFloor;
	}


	@Override
	public void checkCollision() {
		
	}

	@Override 
	public void checkOffScreen() {

	}

	@Override
	public void draw(Graphics2D g) {
		// g.drawRect((int)(getBoundingRect().x), (int)(getBoundingRect().y), (int)(width), (int)(height));
		g.drawRect((int)location.x, (int)location.y, (int)width, (int)height);
	}

}