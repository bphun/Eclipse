import javax.swing.JOptionPane;
import kareltherobot.*;

public class MakeACircle implements Directions {

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
		double circleCircumfrence = 2 * Math.PI * circleRadius;
		
		World.setSize(diameter, diameter);
		robot = new Robot(1,circleRadius, South, infinity);

		goToCenter(robot, circleRadius);

		
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
		
		if (robotDirection != North) {
			turnAround(robot);
		}
		
		
		for (int i = 0; i < circleRadius; i++) {
			robot.move();
		}
	}
}
