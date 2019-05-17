import javax.swing.JOptionPane;

import kareltherobot.*;

public class MakeVShape implements Directions {

	static Robot robot;
	
	public static void main(String[] args) {
		MakeVShape v = new MakeVShape();

		World.setDelay(0);
		
		String v_heightStr = JOptionPane.showInputDialog("What height do you want?");

		int v_height = Integer.parseInt(v_heightStr);

		World.setSize(v_height, v_height * 2);
		World.setVisible(true);

		robot = new Robot(v_height, 1,South,infinity);
		
		v.makeVShape(v_height);

	}

	private void makeVShape(int v_height) {
		int currentBeeper = 0;	
		
		while (currentBeeper < v_height - 1) {
			robot.putBeeper();
			robot.move();
			slideLeft();
			currentBeeper++;
		}
		currentBeeper = 0;
		faceNorth();
		while (currentBeeper < v_height - 1) {
			robot.putBeeper();
			robot.move();
			slideRight();
			currentBeeper++;
		}
	}
	
	private void slideLeft() {
		robot.turnLeft();
		robot.move();
		turnRight();
	}
	
	private void slideRight() {
		turnRight();
		robot.move();
		robot.turnLeft();
	}

	private void faceNorth() {
		if (robot.facingEast()) {
			robot.turnLeft();
		} else if (robot.facingSouth()) {
			turnAround();
		} else if (robot.facingWest()) {
			turnRight();
		}
	}

	private void faceSouth() {
		if (robot.facingEast()) {
			turnRight();
		} else if (robot.facingNorth()) {
			turnAround();
		} else if (robot.facingWest()) {
			robot.turnLeft();
		}
	}
	
	private void turnAround() {
		for (int i = 0; i < 2; i++) {
			robot.turnLeft();
		}	
	}

	private void turnRight() {
		for (int i = 0; i < 3; i++) {
			robot.turnLeft();
		}
	}


}
