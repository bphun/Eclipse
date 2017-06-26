import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Ball {

	private Color color;
	private BallBounce ballBounce;
	protected Location location;
	protected double vX, vY;

	private static final int BALL_DIAMETER = 30;

	public Ball(Location location, BallBounce ballBounce) {
		this.location = location;
		this.ballBounce = ballBounce;
		this.color = Color.RED;
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval((int)location.x(), (int)location.y(), BALL_DIAMETER, BALL_DIAMETER);
		g.setColor(Color.BLACK);

		// color = updateColor(Math.atan2(vY, vX));
	}

	public Color updateColor(double speed) {
		double maxMagnitude = 0.5; // tweak this to get the right color range

		speed /= 4;

		speed = speed < maxMagnitude ? speed : maxMagnitude;

		double H = (speed / maxMagnitude) * 0.38f;  // 0.4f = green
		double S = 0.98f;
		double B = 0.95f;

		return Color.getHSBColor((float)H, (float)S, (float)B);
	}

	public boolean containsPoint(int x, int y) {
		return Math.sqrt(Math.pow(x - location.x(), 2) + Math.pow(y - location.y(), 2)) <= BALL_DIAMETER;
	}

	public boolean willMoveOffScreen() {
		Location newLoc = new Location(location.x() + (BALL_DIAMETER / 2), location.y() + (BALL_DIAMETER / 2));
		newLoc.addX(vX);
		newLoc.addY(vY);

		Rectangle screenRect = new Rectangle(ballBounce.width(), ballBounce.height());

		for (int x = (int)location.x(); x < (int)location.x() + BALL_DIAMETER; x++) {
			for (int y = (int)location.y(); y < (int)location.y() + BALL_DIAMETER; y++) {
				if (this.containsPoint(x, y) && !screenRect.contains(x, y)) {
					return true;
				}
			}
		}
		return false;
	}

	public double radius() {
		return BALL_DIAMETER / 2;
	}

	public int diameter() {
		return BALL_DIAMETER;
	}

	public void didCollide(Ball otherBall) {

	}

}