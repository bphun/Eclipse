import kareltherobot.*;

public class GetArea implements Directions {

	static Robot robot;

	public static void main(String[] args) {
	
		GetArea room = new GetArea();

		World.setDelay(10);
		World.readWorld("basicRoom.wld");
		World.setVisible(true);

		robot = new Robot(7,6, East, infinity);
		
		int area = room.getArea();
		System.out.print("Area: " + area);
	}
	
	private int getArea() {

		int height = 0;
		int width = 0;

		int initialXCoord = robot.avenue();
		int initialYCoord = robot.street();

		faceEast();
		
		while (true) {
		
			if (robot.facingNorth()) {
				height++;	
			} else if (robot.facingEast()) {
				width++;
			}
		
			if (robot.frontIsClear()) {
				robot.move();
			} else if ((!robot.frontIsClear()) && (robot.facingNorth())) {
				robot.turnLeft();
			} else if ((!robot.frontIsClear()) && (robot.facingEast())) {
				robot.turnLeft();
			} else if ((!robot.frontIsClear()) && (robot.facingSouth())) {
				turnRight();
			} else if ((!robot.frontIsClear()) && (robot.facingWest())) {
				robot.turnLeft();	
			}
			
			if ((robot.avenue() == initialXCoord) && (robot.street() == initialYCoord)) {
				break;
			}
		} 

		return height * width;
	}


	private void turnRight() {
		for (int i = 0; i < 3; i++) {
			robot.turnLeft();
		}
	}

	private void faceEast() {
		if (robot.facingNorth()) {
			turnRight();
		} else if (robot.facingSouth()) {
			turnRight();
		} else if (robot.facingWest()) {
			for (int i = 0; i < 2; i++) {
				robot.turnLeft();
			}			
		}
	}
}


