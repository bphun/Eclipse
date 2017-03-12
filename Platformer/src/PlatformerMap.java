import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.util.Iterator;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.util.Set;
import java.util.HashSet;
import javax.swing.Timer;

public class PlatformerMap {

	private List<GameObject> objects;
	private Player player;
	private boolean lostGame;

	private static final int NUM_PLATFORMS = 7;
	private static final String BACKGROUND_IMAGE_FILE_NAME = "";
	private static final int NUM_OPPONENTS = 15;

	private Platform floor;
	private Dimension panelDimension;
	private static Image backgroundImage;

	private List<Integer> platformX;
	private List<Integer> platformY;

	public PlatformerMap(Dimension panelDimension) {
		this.panelDimension = panelDimension;	
		platformX = new ArrayList<>();
		platformY = new ArrayList<>();

//		int i = 0;
//		while (i < 3) {
//			int x = (int)(Math.random() * panelDimension.width) * i;
//			if (!platformX.contains(x)) {
//				platformX.add(x);
//				i++;
//			} 
//		}
//
//		i = 0;
//		while (i < 3) {
//			int y = (int)(Math.random() * (panelDimension.height - 100)) * i;
//
//			if (!platformY.contains(y)) {
//				platformY.add(y);
//				i++;
//			}
//		}

		objects = new ArrayList<GameObject>();
		openBackgroundImage();
		addPlayer();
		player = (Player) objects.get(0);
		addPlatforms();
	}

	public void addGameObject(GameObject go) {
		objects.add(go);
	}
	
	private void addPlatforms() {
		Platform f = new Platform(new Location(0, panelDimension.height - 217), panelDimension.width, (panelDimension.height / 10), this);
		addGameObject(f);
		floor = f;

		if (platformX.isEmpty()) { return; }
		for (int i = 0; i < platformX.size(); i++) {
			addGameObject(new Platform(new Location(platformX.get(i), platformY.get(i)), Type.RECTANGLE.width, Type.RECTANGLE.height, this));
		}

	}

	public void loadOpponents() {
		int currentOpponents = 0;
		//	Create locations of platforms in constructor
		if (objects.size() > 1) {
			for (int i = 0; i < objects.size(); i++) {
				if ((objects.get(i) instanceof Player) && ( ((Player) objects.get(i)).isOpponent() )) {
					currentOpponents++;
				}
			}
		}

		for (int i = currentOpponents; i < NUM_OPPONENTS; i++) {
			addGameObject(new Player(new Location((int) (Math.random() * panelDimension.width), (int) (Math.random() * panelDimension.height)), 30, 30, this));
		}
	}

	private void addPlayer() {
		addGameObject(new Player(new Location(30,panelDimension.height - 900), 30, 30, this));
	}
	
	public void openBackgroundImage() {
		try {		
			URL backgroundImageURL = getClass().getResource(BACKGROUND_IMAGE_FILE_NAME);
			if (backgroundImageURL != null) {
				backgroundImage = ImageIO.read(backgroundImageURL);
				// img = img.getScaledInstance(img.getWidth(null) , img.getHeight(null), Image.SCALE_DEFAULT);
			}
		} catch (IOException e) {
			System.err.println("Could not open image ( " + BACKGROUND_IMAGE_FILE_NAME + " )");
			e.printStackTrace();
		}
	}
	
	int xStart, yStart, xEnd, yEnd;
	public void mouseMoved(MouseEvent e) {
		mouseMoved(e.getX(), e.getY());
	}
	
	public void mouseMoved(int x, int y) {
		xEnd = x;
		yEnd = y;
		xStart = (int) (player.location().getX());
		yStart = (int) (player.location().getY());
		player.aimWeapon(Math.atan2(yEnd - yStart, xEnd - xStart));
	}

	private void checkLostGame() {
		if (player.health() <= 0) {
			objects.clear();	
			lostGame = true;
		}
	}

	public boolean lostGame() {
		return lostGame;
	}

	public void tick() {
		for (GameObject go : objects) {
			go.move();
		}		moveOpponents();
		
		player.shoot();

		for (GameObject go : objects) {
			go.checkCollision();
			go.checkHealth();
		}

		addPlatforms();
		removeFromObjects();
		checkLostGame();
	}
	
	public void playerShoot(boolean shouldShoot) {
		// addGameObject(playerTank.shoot());
		player.updateShooting(shouldShoot);
	}

	public void playerUpdateMotion(Set<Double> directions) {
		player.updateMotion(directions);
	}

	public Dimension dimensions() {
		return panelDimension;
	}

	public void moveOpponents() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject go = objects.get(i);
			if (go instanceof Player && ((Player)go).isOpponent()) {
				Player t = (Player) go;
				t.opponentMove();
			}
		}
	}

	public void removeFromObjects() {
		int i = 0;

		while (i < objects.size()) {
			if (objects.get(i).shouldRemove()) {
				objects.remove(i);
			} else {
				i++;
			}
		}
//		while (iter.hasNext()) {
//			GameObject gameObject = iter.next();
//			for (GameObject go : removeObjects) {
//				if (gameObject.equals(go)) {
//					iter.remove();
//					return;
//				}
//			}
//		}
	}

	public List<Platform> platforms() {
		List<Platform> platforms = new ArrayList<>();
		for (GameObject go : objects) {
			if (go instanceof Platform) {
				platforms.add((Platform)go);
			}
		}
		return platforms;
	}

	public List<GameObject> objects() {
		return objects;
	}

	public void draw(Graphics2D g) {
		if (lostGame == true) {
			g.drawString("Game Over, click to restart", (panelDimension.width / 2), panelDimension.height / 2);
		}

		for (GameObject go : objects) {
			g.setColor(Color.BLACK);
			go.draw(g);
		}
	}

}
