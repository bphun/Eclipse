
import kareltherobot.*;

public class MakeVShape implements Directions {

	Robot robot = new Robot(1,1,North,infinity);
	
	public static void main(String[] args) {
		MakeVShape v = new MakeVShape();

		World.setDelay(10);
		World.setVisible(true);
		v.makeVShape(5, 5);
	}

	private void makeVShape(int V_height, int V_length) {
	
		faceNorth();
		
		for (int length = 0; length < V_length; length++) {
			
			faceNorth();
			for (int heightUp = 0; heightUp < V_height; heightUp++) {
				robot.move();
				if (heightUp == V_height) {
					robot.putBeeper();
				}
			}
			
			V_height--;
			slideRight();
			turnAround();
			
			for (int heightDown = 0; heightDown < V_height; heightDown++) {
				robot.move();
			}
			slideLeft();			
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
		System.out.println("Turning north");
		if (robot.facingEast()) {
			robot.turnLeft();
		} else if (robot.facingSouth()) {
			turnAround();
		} else if (robot.facingWest()) {
			turnRight();
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
