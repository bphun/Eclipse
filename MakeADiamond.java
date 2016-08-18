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
      
      drawBottomLeft(robot);
          
      drawTopLeft(robot);
	} 
	
	void drawBottomLeft(Robot robot) {
	    while (currentBeeper < beeperCount / 4 - 1) {
                robot.putBeeper();
                robot.move();
                robot.turnLeft();
                robot.move();
                robot.putBeeper();
                if (currentBeeper != 4) {
                     turnRight(robot);
                }
        }
          currentBeeper = null;
	}
	
  void drawTopLeft(Robot robot) {
      while (currentBeeper < beeperCount / 4 - 1) {
                 robot.move();        // Might have to change to 'turnRight(robot)'
                 turnRight(robot);
                 robot.move();
                 robot.putBeeper();
                 robot.turnLeft();
                 //wrobot.move();
                 //turnRight(robot);
                 //robot.move();
                 //robot.putBeeper();                       
                 currentBeeper++;                                                      
      }
      currentBeeper = null;
  }

  void turnRight(Robot robot) {
      robot.turnLeft();
      robot.turnLeft();
      robot.turnLeft();
      
   }
           
}
