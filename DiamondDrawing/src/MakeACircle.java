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
		double circleCircumfrence = 2 * Math.PI * circleRadius;
		
		World.setSize(diameter + 2, diameter + 2);
		robot = new Robot(circleRadius + 2,circleRadius + 2, North, infinity);
		robot.putBeeper();
		
		for (int quadrants = 0; quadrants <= 3; quadrants++) {
			for (int i = 0; i < circleRadius; i ++) {
				robot.move();
			}
			robot.putBeeper();
			goToCenter(robot, circleRadius);
			robot.turnLeft();		
		}
		
		moveLeft(robot);
		slideCount++;
		
		int currentXPosition = circleRadius - slideCount;
		
		for (int steps = 0; steps < circleRadius; steps++) {
			generateNewYValue(circleRadius, currentXPosition);
		}
		
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
		
		Direction robotDirection = robot.direction();
		
		turnAround(robot);
		
		for (int i = 0; i < circleRadius; i++) {
			robot.move();
		}
	}
	
	private void moveLeft(Robot robot) {
		robot.turnLeft();
		robot.move();
		robot.turnLeft();
		turnAround(robot);
	}
	
	private int[] generateNewYValue(int circleRadius, int currentXPosition) {
		
		int[] cValueArray = new int[0];
				
		for (int yValue = 0; yValue <= circleRadius; yValue++) {
						
			int a = circleRadius;
			int b = circleRadius - slideCount;
			int c;
					
			c = (a * a) + (b * b);
			c = (int) Math.sqrt(c);
			 
//			cValueArray[yValue] = c;
			
			System.out.println("C Value: " + c);
			slideCount--;
		}
		return cValueArray;
	}
	
		
}


