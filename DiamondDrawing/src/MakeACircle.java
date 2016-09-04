import javax.swing.JOptionPane;
import kareltherobot.*;

public class MakeACircle implements Directions {
		
	static int circleDiameter;
	int slideCount;
	
	public static void main(String[] args) {
		MakeACircle circle = new MakeACircle();
		
		//	Prompt the user for their desired circle diameter then convert that value from a 'String' to an 'int'
		String circleDiameterStr = JOptionPane.showInputDialog(null, "What diameter circle would you like");
		circleDiameter = Integer.parseInt(circleDiameterStr);
		
		//	Make the world visible, and set the delay to '0' so that Karel draws the circle quickly
		World.setVisible(true);
		World.setDelay(0);
		
		//	Draw the circle
		circle.drawCircle(circleDiameter);	
	}
	
	private void drawCircle(int diameter) {
		Robot robot;
		
		int circleRadius = diameter / 2;
		
		//	Set the worlds size to 'diameters + 1' so that we can fit the circle into the world
		World.setSize(diameter + 1, diameter + 1);
		
		//	Add Karel to the center of the view, facing North, and turn off the beeper limit
		robot = new Robot(circleRadius + 1,circleRadius + 1, North, infinity);
		
		//	Put a beeper at the center of the world so that we can mark the circle's center point
		robot.putBeeper();
				
		//	Slide Karel all of the way to the right then slide him to the bottom of the world, we'll use this as our starting point
		for (int slides = 0; slides < circleRadius; slides++) {
			slideRight(robot);
		}
		turnAround(robot);		
		for (int i = 0; i < circleRadius; i++) {
			robot.move();
		}
		turnAround(robot);
		
		//	MARK:	Begin drawing the circle
		
		//	Create a loop that iterates through all of the x values on the coordinate plane
		for (int loopTimes = circleDiameter; loopTimes > 0; loopTimes--) {
			
			//	Move Karel to the top of the world, checking our distance from the circle's center point every move
			for(int stepsUp = 0; stepsUp < circleRadius * 2; stepsUp++) {
				robot.move();
				checkDistanceFromCenter(robot, circleRadius);
			}
			
			//	Set up Karel to begin the next row
			slideLeft(robot);
			checkDistanceFromCenter(robot, circleRadius);
			turnAround(robot);
			
			//	Move karel to the bottom of the world, checking our distance from the circle's center point every move
			for (int stepsDown = 0; stepsDown < circleRadius * 2; stepsDown++) {
				robot.move();
				checkDistanceFromCenter(robot, circleRadius);
			}
			
			//	Set up Karel to begin the next row
			slideRight(robot);
			checkDistanceFromCenter(robot, circleRadius);
			turnAround(robot);	
		}	
	}
	
	//	Method used to check Karel's distance from the circle's center point
	private int getDistanceFromCenter(int currentXPos, int currentYPos, int centerXCord, int centerYCord) {
		//	Note: Distance Formula: d = √(x2 - x1)^2 + (y2 - y1)^2
		
		int distanceFromCenter;
		int xDif = currentXPos - centerXCord;
		int yDif = currentYPos - centerYCord;
		
		distanceFromCenter = (int) Math.sqrt((xDif * xDif) + (yDif * yDif));
				
		return distanceFromCenter;
	}
	
	//	Method used to check if Karel's distance from the circle's center point is equal to the circle's desired radius, if it is then we will put down a beeper	
	private void checkDistanceFromCenter(Robot robot, int circleRadius) {
		int currentXPos = robot.avenue() - 1;
		int currentYPos = robot.street() - 1;
		
		int circleCenterX = circleRadius;
		int circleCenterY = circleRadius;
				
		int distanceFromCenter = getDistanceFromCenter(currentXPos, currentYPos, circleCenterX, circleCenterY);
				
		if (distanceFromCenter == circleRadius) {
			robot.putBeeper();
		}
	}
	
	//	MARK:	Karel movement helper methods
	
	private void turnRight(Robot robot) {
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}
	
	private void turnAround(Robot robot) {
		robot.turnLeft();
		robot.turnLeft();
	}
	
	private void slideLeft(Robot robot) {
		robot.turnLeft();
		robot.move();
		turnRight(robot);
		slideCount++;
	}
	
	private void slideRight(Robot robot) {
		turnRight(robot);
		robot.move();
		robot.turnLeft();
		slideCount++;
	}
}


