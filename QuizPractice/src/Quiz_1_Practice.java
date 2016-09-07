import javax.swing.JOptionPane;

import kareltherobot.*;

public class Quiz_1_Practice implements Directions {

	static Robot robot;	

	public static void main(String[] args) {

		Quiz_1_Practice room = new Quiz_1_Practice();

		robot = new Robot(1,1,North,infinity);

		World.setDelay(1);
		
		String triangleHeightStr = JOptionPane.showInputDialog("What height triangle do you want?");
		int triangleHeight = Integer.parseInt(triangleHeightStr);
		
		String triangleWidthStr = JOptionPane.showInputDialog("What width triangle do you want?");
		int triangleWidth = Integer.parseInt(triangleWidthStr);
		
		World.setSize(triangleHeight + 3, triangleWidth + 3);
		World.setVisible(true);

		room.drawTriangle(triangleHeight, triangleWidth);
	}
	
	private void drawTriangle(int height, int width) {
				
		int slope = (height / width);
		
		while (width >= 0) {
			
			faceNorth();
			for (int upHeight = 0; upHeight < height; upHeight++) {
				robot.move();
				robot.putBeeper();
			}
			turnAround();
			for (int downHeight = 0; downHeight < height; downHeight++) {
				robot.move();
			}
			slideLeft();
			height = height - slope;
			width--;
		}

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

	private void slideLeft() {
		robot.turnLeft();
		robot.move();
		robot.turnLeft();
	}
	
	private void slideRight() {
		turnRight();
		robot.move();
		robot.turnLeft();
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
