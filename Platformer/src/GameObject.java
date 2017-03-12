import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;

public abstract class GameObject {

	protected double vY;
	protected double speed;
	protected double direction; // in radians
	protected double width; //horizontal width of the object
	protected double height; // vertical height of the object
	protected double health; // 0 - 100
	private Color color;
	private Image img;
	protected Location location;
	private boolean shouldRemove;
	protected PlatformerMap map;
	protected boolean hitFloor;
	protected boolean inAir;

	public GameObject(Location location, double direction, double speed, double width, double height,
			Color color, Image img, PlatformerMap map) {
		this.location = location;
		this.direction = direction;
		// this.vX = speed;
		// this.vY = speed;
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.color = color;
		this.img = img;
		this.shouldRemove = false;
		this.map = map;
	}

	public GameObject(Location location, double width, double height, PlatformerMap map) {
		this(location, 0, 0, width, height, null, null, map);
	}

	public double health() {
		return health;
	}

	public void move() {
		if (willMoveOffscreen()) {
			checkOffScreen();
		} else {
			location.addVector(speed, direction);
		}
	}

	private boolean willMoveOffscreen() {
		Location loc = new Location(location);
		loc.addVector(speed, direction);
		return !loc.inMap(new Rectangle(map.dimensions()));
	}

	public abstract void checkCollision();

	public abstract void checkOffScreen();

	public abstract void draw(Graphics2D g);

	public Rectangle getBoundingRect() {
		return new Rectangle((int) (location.getX()),
				(int) (location.getY()),
				(int) width, (int) height + 2);
	}

	public void markRemove() {
		shouldRemove = true;
	}

	public boolean didHitFloor() {
		return hitFloor;
	}

	public void hitFloor() {
		hitFloor = true;
	}

	public void notHitFloor() {
		hitFloor = false;
	}

	public boolean shouldRemove() {
		return shouldRemove;
	}
	
	public void checkHealth() {
		if (health <= 0) {
			markRemove();
			// System.out.println("Thing at "+location+" died");
		}
	}
	
	public PlatformerMap getMap() {
		return map;
	}
}
