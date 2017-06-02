import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject {

	private Player owner;

	public Bullet(Location location, double direction, double speed, double width, double height, Player owner, PlatformerMap map) {
		super(location, direction, speed, width, height, null, null, map);
		health = 5;
		this.owner = owner;
	}

	public boolean isOwner(Player player) {
		return owner.equals(player);
	}

	public Player getOwner() {
		return owner;
	}

	@Override
	public void checkOffScreen() {
		markRemove();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		Rectangle bound = getBoundingRect();
		g.fillOval(bound.x, bound.y, bound.width, bound.height);
		// Rectangle boundingRect = getBoundingRect();
		// g.drawRect(boundingRect.x, boundingRect.y, boundingRect.width, boundingRect.height);
	}

	@Override
	public void checkCollision() {
		for (int i = 0; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);
			if (go == this) {
				continue;
			}
			if (go instanceof Player) {
				checkCollision((Player) go);
				continue;
			}  
			// if (go instanceof Platform) {
			// 	// markRemove();
			// 	continue;
			// }
			if (getBoundingRect().intersects(go.getBoundingRect())) {
				// System.out.println("Bullet at "+location+" should be removed");
				// System.out.println("Bullet hit thing at "+go.location);
				// System.out.println(go.getBoundingRect().intersects(getBoundingRect()));
				markRemove();
			}
		}
	}

	private void checkCollision(Player p) {
		if (isOwner(p)) {
			return;
		}
		if (getBoundingRect().intersects(p.getBoundingRect())) {
			p.health -= 5;
			markRemove();
		}
	}

}
