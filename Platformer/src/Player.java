import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Set;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.Point;
import java.util.List;

public class Player extends GameObject {

	private final static double GRAVITY = -1.10665;
	private final static double GRAVITYX2 = GRAVITY * 2;
	private final static double HALF_GRAVITY = GRAVITY / 2;

	private Weapon weapon;
	private boolean isOpponent;
	private OpponentLogic opponentLogic;
	private int numKills;
	private boolean canJump;
	private boolean justLanded;
	private long lastTime;
	private static Timer t;
	private int timeOfFlight;

	public Player(Location location, double width, double height, PlatformerMap map) {
		super(location, width, height, map);
		weapon = new Weapon(direction, this);
		health = 100;
		this.isOpponent = isOpponent;
		numKills = 0;
		canJump = true;
		timeOfFlight = 1;

		if (isOpponent) {
			this.opponentLogic = new OpponentLogic(map, this);
			weapon.setFiringStatus(true);
		}

		if (t == null) {
			t = new Timer(1, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					fall();
				}	
			});
			t.start();
		}
	}

	private void fall() {
		List<Platform> platforms = map.platforms();
		if (!didHitFloor()) {
			Platform p = null;
			for (Platform platform : platforms) {
				if (platform.getBoundingRect().contains(platform.getBoundingRect())) {
					p = platform;
				}
			}

			if (p != null && this.location.getY() + this.height < p.location.getY()) {
				this.location.addY(vY - HALF_GRAVITY);
				canJump = false;
			}
		}
	}

	public void shoot() {
		if (weapon.readyToFire()) {
			map.addGameObject(weapon.shoot(new Location(location), 5.0));
			weapon.resetCooldown();
		}
	}

	public void incNumKills() {
		numKills++;
	}

	public double getBulletSpeed(){
		return 5 + (int)numKills / 5;
	}
	

	public void updateShooting(boolean shouldShoot) {
		weapon.setFiringStatus(shouldShoot);
	}

	public boolean isOpponent() {
		return isOpponent;
	}

	public void updateMotion(Set<Double> directions) {
		if (directions.isEmpty()) {
			if (inAir) {
				vY = 0;
				// hitFloor = false;
			} else if (hitFloor) {
				speed = 0;
				vY = 0;
			}
		} else {
			double dx = 0;
			double dy = 0;
			for (Double d : directions) {
				dx += Math.cos(d);
				dy += Math.sin(d);
			}
			if (Math.abs(dx) <.001 && Math.abs(dy)<.001) {
				speed = 0;
			} else {
				direction = Math.atan2(dy, dx);
				if ((direction < 0 && direction <= -Math.PI / 2) || (direction > Math.PI && direction <= -Math.PI / 2)) {
					if (canJump) {
						vY = 1;
						inAir = true;
					}
				} else if (direction == 0 || direction == Math.PI) {
					speed = 1;
				}
			}
		}
		if (hitFloor) {
			hitFloor = false;
		}
	}	

	private void inAir() {
		if (inAir) {
			// location.addY(Math.sqrt(Math.pow(speed, 2) - GRAVITYX2));
		}
	}

	public void aimWeapon(double direction) {
		weapon.aim(direction);
	}

	public Location location() {
		return location;
	}

	public void opponentMove() {
		opponentLogic.opponentMove();
	}

	@Override
	public void checkOffScreen() {
		direction -= Math.PI; // Turn the tank around
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		Rectangle bound = getBoundingRect();
		g.drawRect(bound.x, bound.y, bound.width, bound.height);
		if (!isOpponent) {
			g.setColor(Color.CYAN);
		}
		g.fillOval(bound.x, bound.y, bound.width, bound.height);

		g.setColor(Color.GREEN);
		g.fillRect(bound.x, bound.y - 6, (int) (health) / 3, 4);
		// g.drawRect(bound.x, bound.y + 32, bound.width, 5);
	}

	@Override
	public void checkCollision() {		
		for (int i = 0; i < map.objects().size(); i++) {
			GameObject go = map.objects().get(i);
			if (this == go) {
				continue;
			}
			if (go instanceof Platform) {
				checkCollision((Platform) go);
				continue;
			}
		}
	}

	private void checkCollision(Platform p) {
		if (this.getBoundingRect().intersects(p.getBoundingRect())) {
			this.hitFloor();
			this.canJump = true;
			this.vY = 0;
		}
	}

	@Override
	public void move() {
		super.move();
		weapon.tickCooldown();
	}

}
