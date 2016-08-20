import java.util.Scanner;

import javax.swing.JOptionPane;

import kareltherobot.*;


public class MakeADiamond  implements Directions{ 

//	Robot r = new Robot(1,5,North,30); 

	public static void main(String[] args) { 
		MakeADiamond diamond = new MakeADiamond(); 
//		World.setDelay(0);
		World.setVisible(true);
		
		String sideLengthPrompt = JOptionPane.showInputDialog(null, "How large of a side do you want?");
		int sideLength = Integer.parseInt(sideLengthPrompt);	
				
		diamond.drawDiamond(sideLength);
	}

	private void drawDiamond(int sideLength) {
		Robot robot;
				
		if (sideLength > 5) {
			World.setSize(sideLength * 2, sideLength *  2);
			robot = new Robot(1, sideLength, North, 30);
		} else {
			 robot = new Robot(1,5,North,30);
		}
		
		final int beeperCount = sideLength * 4;
		System.out.print("Beepers to Draw: " + beeperCount);
		for (int sideCount = 0; sideCount <= 3; sideCount++) {
			
			System.out.println("Drawing Side: " + sideCount);
			
			for (int currentBeeper = 0;  currentBeeper < (beeperCount / 4) - 1; currentBeeper++) {
				robot.putBeeper();
				robot.move();
				robot.turnLeft();
				robot.move();
				turnRight(robot);
				
				int remainingBeepers = beeperCount - currentBeeper;
				System.out.println("Remaining Beepers: " + remainingBeepers);
				
			}
			turnRight(robot);
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
}

//for (int sideCount = 0; sideCount <= 3; sideCount++) {
//	
//	System.out.println("Side Count: " + sideCount);
//	
//	switch (sideCount) {
//		case 0:
//			while (currentBeeper <= beeperCount / 4 - 1) {
//				if (currentBeeper == 0) {
//					robot.putBeeper();
//				} else if (currentBeeper > 0) {
//					robot.move();
//					robot.turnLeft();
//					robot.move();
//					robot.putBeeper();
//					turnRight(robot);	
//				}
//				currentBeeper++;					
//			}
//	
//		case 1:
//			while (currentBeeper <= beeperCount / 4 - 1) {
//				if (currentBeeper != 4) {
//					robot.move();        
//					turnRight(robot);
//					robot.move();
//					robot.putBeeper();
//					robot.turnLeft();
//				} else {
//					turnAround(robot);
//				}
//				currentBeeper++;  	
//			}
//	
//		case 2:
//			while (currentBeeper <= beeperCount / 4 - 1) {
//				if (currentBeeper != 4) {
//				robot.move();
//				robot.turnLeft();
//				robot.move();
//				robot.putBeeper();
//				turnRight(robot);	
//				}
//				currentBeeper++;	
//			}
//
//		case 3:
//			while (currentBeeper <= beeperCount / 4 - 1) {
//				if (currentBeeper != 4) {
//					robot.move();
//					turnRight(robot);
//					robot.move();
//					robot.putBeeper();
//					robot.turnLeft();	
//				}
//				currentBeeper++;	
//			}
//		default:
//			break;	
//	}
//}