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
		int beeperCount = 20;
                int currentBeeper = 0;

                while (currentBeeper < 4) {
                      robot.putBeeper();
             

                }
	} 

	


}
