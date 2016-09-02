
import kareltherobot.*;

public class Quiz_1_Practice implements Directions {

	Robot robot = new Robot(1,1,East, infinity);

	public static void main(String[] args) {
		Quiz_1_Practice q = new Quiz_1_Practice();
		
		World.setDelay(10);
		World.setVisible(true);
		q.drawTriangle(5, 5);	
	}

	private void drawTriangle(int triangleHeight, int triangleBaseLength) {
		
		int triangleArea = (triangleHeight * triangleBaseLength) / 2;
		int beeperCount = 0;
		
		while (beeperCount <= triangleArea) {
			
			faceNorth();
			
			
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





