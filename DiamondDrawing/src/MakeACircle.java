import javax.swing.JOptionPane;
import kareltherobot.*;

public class MakeACircle implements Directions {
		
	int slideCount;
	
	public static void main(String[] args) {
		MakeACircle circle = new MakeACircle();
		
		String circleDiameterStr = JOptionPane.showInputDialog(null, "What diameter circle would you like");
		int circleDiameter = Integer.parseInt(circleDiameterStr);
		
		World.setVisible(true);
		World.setDelay(10);
		
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
	
	private beeper[] generateBeeperCoordinate(int circleRadius, Robot robot) {
		int circleCenterX = circleRadius;
		int circleCenterY = circleRadius;
		
		int currentXPos = robot.street();
		int currentYPos = robot.avenue();
		
		int distanceToCenter = getDistanceFromCenter(currentXPos, currentYPos, circleCenterX, circleCenterY);	
		int circleCircumfrence = (int) (2 * Math.PI * circleRadius);
		
		int currentCord = 0;
		
		beeper beeperCord;
		beeper[] beeperCoordinateArray = new beeper[0];
				
		while (beeperCoordinateArray.length < circleCircumfrence / 4) {
			
			for (int quadrants = 0; quadrants <= 3; quadrants++) {
				
				slideLeft(robot);
				for (int steps = 0; steps < circleRadius; steps ++) {
					int distance = (int) (Math.sqrt((circleRadius * circleRadius - steps*steps)));
					distance = (int) Math.round(distance);
					
					move(distance, robot);
				}
				robot.putBeeper();
				goToCenter(robot, circleRadius);
				robot.turnLeft();		
			}
			
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

	private void move(int n, Robot robot) {
		while (n > 0) {
			robot.move();
			n--;
		}
	}
}

class beeper {
	
	int xCord;
	int yCord;
	
	beeper(int beeperXCord, int beeperYCord) {
		beeperXCord = xCord;
		beeperYCord = yCord;
	}
}
