import java.util.Scanner; 

import kareltherobot.*;


public class MakeADiamond  implements Directions{ 

//	Robot r = new Robot(1,5,North,30); 

	public static void main(String[] args) { 
		MakeADiamond diamond = new MakeADiamond(); 
		World.setVisible(true);
		
		diamond.drawDiamond();
	}

	private void drawDiamond() {
		Robot robot = new Robot(1,5,North,30);
		int vertCount = 4;
		
		for (int i = 0; i <= vertCount; i++) {
			System.out.println("Drawing Vert:" + i);
			robot.move();
			robot.putBeeper();
			robot.turnLeft();
		}
		
		for (int currentVert = 0; currentVert <= vertCount; currentVert++) {
			robot.move();
			robot.pickBeeper();
			robot.turnLeft();
		}
	} 

	


}