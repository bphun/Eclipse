import javax.swing.Timer;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallBounce {

	private Timer t;
	private Arrow arrow;
	private JFrame frame;
	private BallBouncePanel ballPanel;
	private ControlPanel controlPanel;
	private ArrayList<Ball> balls;

	private long previousTime;
	private long currentTime;
	private long elapsedTime;

	private static double gravity;

	private static final int REFRESH_INTERVAL = 5;
	private static final int CONTROL_PANEL_HEIGHT = 65;
	private static final Dimension DIMENSION = new Dimension(900, 600);

	public static void main(String[] args) {
		new BallBounce().start();
	}

	private void start() {
		frame = new JFrame("Ball Bounce");
		ballPanel = new BallBouncePanel(DIMENSION, this);
		controlPanel = new ControlPanel(width(), CONTROL_PANEL_HEIGHT, this);

		frame.add(ballPanel, BorderLayout.CENTER);
		frame.add(controlPanel, BorderLayout.PAGE_END);

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gravity = 0.0122;

		balls = new ArrayList<>();
		
		startTimer();
	}	

	private void startTimer() {

		previousTime = System.currentTimeMillis();
		currentTime = previousTime;

		t = new Timer(REFRESH_INTERVAL, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		t.start();
	}

	private void refresh() {
		currentTime = System.currentTimeMillis();
		elapsedTime = (currentTime - previousTime); // elapsed time in seconds

		updateBalls(elapsedTime);
		ballPanel.repaint();

		previousTime = currentTime;

	}

	private void updateBalls(float elapsedTime) {
		for (Ball b : balls) {
			b.vY += (gravity * elapsedTime);

			b.vX += b.vX * elapsedTime;
			b.vY += b.vX * elapsedTime;

			b.location.addX(b.vX);
			b.location.addY(b.vY);

			checkCollision(b);
		}
	}

	private void checkCollision(Ball b) {
		for (Ball ball : balls) {
			if (b.equals(ball)) { continue; }

			if (b.containsPoint((int)ball.location.x(), (int)ball.location.y())) {
				System.out.println("Ball intersect");
			} else if (b.willMoveOffScreen()) {
				System.out.println("willMoveOffScreen");
			}

		}
	}

	public Arrow arrow() {
		return arrow;
	}

	public ArrayList<Ball> balls() {
		return balls;
	}

	public void updateArrow(int newXEnd, int newYEnd) {
		arrow.setMouseX(newXEnd);
		arrow.setMouseY(newYEnd);
	}

	public void releaseArrow() {
		arrow = null;
	}

	public void addBall(int x, int y) {
		balls.add(new Ball( new Location(x, y), this));
		arrow = new Arrow(x, y, x, y);
	}

	public void scatterButtonAction() {

	}

	public void generateButtonAction() {

	}

	public void resetButtonAction() {

	}

	public void gravitySliderDidChange(int val) {

	}

	public int width() {
		return DIMENSION.width;
	}

	public int height() {
		return DIMENSION.height - CONTROL_PANEL_HEIGHT;
	}

}