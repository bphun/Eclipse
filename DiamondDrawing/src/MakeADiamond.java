import java.util.Scanner; 

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
		final int beeperCount = 20;
		int currentBeeper = 0;
      
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
	

	void turnRight(Robot robot) {
     	robot.turnLeft();
     	robot.turnLeft();
     	robot.turnLeft(); 
	}
           
	void turnAround(Robot robot) {
     	robot.turnLeft();
     	robot.turnLeft();
	}
}