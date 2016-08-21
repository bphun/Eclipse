import javax.swing.JOptionPane;
import kareltherobot.*;

public class MakeACircle implements Directions {

	public static void main(String[] args) {
		MakeACircle circle = new MakeACircle();
		
		String circleDiameterStr = JOptionPane.showInputDialog(null, "What diameter circle would you like");
		int circleDiameter = Integer.parseInt(circleDiameterStr);
		
		World.setVisible(true);
		
		circle.drawCircle(circleDiameter);
		
	}
	
	private void drawCircle(int diameter) {
		Robot robot = new Robot(1,5, North, infinity);
		
		int circleDiameter = diameter / 2;
		
		
		
		
	}
	
	private void turnRight(Robot robot) {
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}
	
}
