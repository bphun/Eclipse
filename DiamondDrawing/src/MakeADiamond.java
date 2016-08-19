import java.util.Scanner;

import javax.swing.JOptionPane;

import kareltherobot.*;


public class MakeADiamond  implements Directions{ 

//	Robot r = new Robot(1,5,North,30); 

	public static void main(String[] args) { 
		MakeADiamond diamond = new MakeADiamond(); 
		World.setVisible(true);
		World.setDelay(3);
		
		diamond.drawDiamond();
	}

	private void drawDiamond() {
		Robot robot = new Robot(1,5,North,30);
		int currentBeeper = 0;
      
		String sideLengthPrompt = JOptionPane.showInputDialog(null, "How large of a side do you want?");
		int sideLength = Integer.parseInt(sideLengthPrompt);		
		
		if (sideLength > 5) {
			World.setSize(sideLength * 2, sideLength *  2);
		}
		final int beeperCount = sideLength * 4;
		
		while (currentBeeper <= beeperCount / 4 - 1) {
			if (currentBeeper == 0) {
				robot.putBeeper();
	    	} else if (currentBeeper > 0) {
		    	robot.move();
		    	robot.turnLeft();
		    	robot.move();
		    	robot.putBeeper();
		    	turnRight(robot);	
	    	}
			currentBeeper++;
        }	
				
		currentBeeper = 0;          
		
		while (currentBeeper <= beeperCount / 4 - 1) {
			if (currentBeeper != 4) {
				robot.move();        
				turnRight(robot);
				robot.move();
				robot.putBeeper();
	          	robot.turnLeft();
			} else {
				turnAround(robot);
			}
            currentBeeper++;                                                      
		}
		currentBeeper = 0;
		
		while (currentBeeper <= beeperCount / 4 - 1) {
			if (currentBeeper != 4) {
				robot.move();
				robot.turnLeft();
				robot.move();
				robot.putBeeper();
				turnRight(robot);	
			}
			currentBeeper++;
		}
		currentBeeper = 0;
		
		while (currentBeeper <= beeperCount / 4 - 1) {
			if (currentBeeper != 4) {
				robot.move();
				turnRight(robot);
				robot.move();
				robot.putBeeper();
				robot.turnLeft();	
			}
			currentBeeper++;
		}
		currentBeeper = 0;	
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
}