
import javax.swing.JOptionPane;

import kareltherobot.*;

public class MakeVShape implements Directions {

	static Robot robot;
	
	public static void main(String[] args) {
		MakeVShape v = new MakeVShape();

		World.setDelay(0);
		
		String V_heightStr = JOptionPane.showInputDialog("What height do you want?");
 
		int V_height = Integer.parseInt(V_heightStr);

		World.setSize(V_height, V_height * 2);
		World.setVisible(true);

		robot = new Robot(V_height, 1,South,infinity);
		
		v.makeVShape(V_height);

	}

	private void makeVShape(int V_height) {
		int currentBeeper = 0;	
		
			while (currentBeeper < V_height - 1) {
				robot.putBeeper();
				robot.move();
				slideLeft();
				currentBeeper++;
			}
			currentBeeper = 0;
			faceNorth();
			while (currentBeeper < V_height - 1) {
				robot.putBeeper();
				robot.move();
				slideRight();
				currentBeeper++;
			}
			currentBeeper = 0;


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
		robot.turnLeft();
		robot.turnLeft();	
	}

	private void turnRight() {
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}


}
