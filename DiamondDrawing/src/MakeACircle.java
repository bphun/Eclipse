import javax.swing.JOptionPane;
import kareltherobot.*;

public class MakeACircle implements Directions {
		
	int slideCount;
	
	public static void main(String[] args) {
		MakeACircle circle = new MakeACircle();
		
		String circleDiameterStr = JOptionPane.showInputDialog(null, "What diameter circle would you like");
		int circleDiameter = Integer.parseInt(circleDiameterStr);
		
		World.setVisible(true);
		World.setDelay(0);
		
		circle.drawCircle(circleDiameter);	
	}
	
	private void drawCircle(int diameter) {
		Robot robot;
		int circleRadius = diameter / 2;
		int circleCenterX = circleRadius;
		int circleCenterY = circleRadius;
		
		World.setSize(diameter + 2, diameter + 2);
		robot = new Robot(circleRadius + 2,circleRadius + 2, North, infinity);
		robot.putBeeper();	//	Place a beeper to mark the center point
		
		for (int quadrants = 0; quadrants <= 3; quadrants++) {
			for (int i = 0; i < circleRadius; i ++) {
				robot.move();
			}
			robot.putBeeper();
			goToCenter(robot, circleRadius);
			robot.turnLeft();		
		}	
		generateBeeperCoordinate(circleRadius, robot);
		
	}
	
	private beeperCoordinate[] generateBeeperCoordinate(int circleRadius, Robot robot) {
		int circleCenterX = circleRadius;
		int circleCenterY = circleRadius;
		
		int currentXPos = robot.street();
		int currentYPos = robot.avenue();
		
		int distanceToCenter = getDistanceFromCenter(currentXPos, currentYPos, circleCenterX, circleCenterY);	
		int circleCircumfrence = (int) (2 * Math.PI * circleRadius);
		
		int currentCord = 0;
		
		beeperCoordinate[] beeperCoordinateArray = new beeperCoordinate[circleCircumfrence / 4];
		
		System.out.print("# of beepers: " + circleCircumfrence / 4);
		
		while (true) {
			int distanceFromCenter = getDistanceFromCenter(currentXPos, currentYPos, circleCenterX, circleCenterY);
			beeperCoordinate beeperCord;
			
			while (beeperCoordinateArray.length <= circleCircumfrence / 4) {
				currentXPos = robot.street();
				currentYPos = robot.avenue();
				
				//	TODO: Go up and down every X coordinate checking if the current coordinate allows to draw a line with a distance to the center of 10
				
				if (distanceFromCenter >= circleRadius) {
					
					beeperCord = new beeperCoordinate(currentXPos, currentYPos);
									
					beeperCoordinateArray[currentCord] = beeperCord;	
					currentCord++;
				} else {
					continue;
				}
			}
			break;
		}
		return beeperCoordinateArray;
	}
	
	private int getDistanceFromCenter(int currentXPos, int currentYPos, int centerXCord, int centerYCord) {
		int xDif = currentXPos - centerXCord;
		int yDif = currentYPos - centerYCord;
		
		int distanceFromCenter = (xDif * xDif) + (yDif * yDif);
		distanceFromCenter = (int) (Math.sqrt(distanceFromCenter));
		distanceFromCenter = Math.round(distanceFromCenter);
		
		System.out.print("Distance from center: " + distanceFromCenter + "\n");
		
		return distanceFromCenter;
	}
		
	private void turnRight(Robot robot) {
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}
	
	private void turnAround(Robot robot) {
		robot.turnLeft();
		robot.turnLeft();
	}
	
	private void goToCenter(Robot robot, int circleRadius) {	
		turnAround(robot);
		for (int i = 0; i < circleRadius; i++) {
			robot.move();
		}
	}
	
	private void slideLeft(Robot robot) {
		robot.turnLeft();
		robot.move();
		turnRight(robot);
		slideCount++;
		System.out.println("Slide count: " + slideCount);
	}
	
	private void slideRight(Robot robot) {
		turnRight(robot);
		robot.move();
		robot.turnLeft();
		slideCount++;
	}
}

class beeperCoordinate {
	
	int xCord;
	int yCord;
	
	beeperCoordinate(int beeperXCord, int beeperYCord) {
		beeperXCord = xCord;
		beeperYCord = yCord;
	}
}


