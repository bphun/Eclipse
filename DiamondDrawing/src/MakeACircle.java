import javax.swing.JOptionPane;
import kareltherobot.*;

public class MakeACircle implements Directions {

	public static void main(String[] args) {
		MakeACircle circle = new MakeACircle();
		
		String circleDiameterStr = JOptionPane.showInputDialog(null, "What diameter circle would you like");
		int circleDiameter = Integer.parseInt(circleDiameterStr);
		
		World.setVisible(true);
		World.setDelay(20);
		
		circle.drawCircle(circleDiameter);
		
	}
	
	private void drawCircle(int diameter) {
		Robot robot;
		int circleRadius = diameter / 2;
		
		World.setSize(diameter, diameter);
		robot = new Robot(1,circleRadius, North, infinity);
		
		robot.putBeeper();
		robot.move();
		turnRight(robot);
		robot.move();
		robot.putBeeper();
		robot.move();
		robot.turnLeft();
		robot.move();
		robot.putBeeper();
		robot.move();
		robot.turnLeft();
		robot.move();
		robot.putBeeper();
		robot.move();
		robot.putBeeper();

		
	}
	
	private void turnRight(Robot robot) {
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}
	
}
