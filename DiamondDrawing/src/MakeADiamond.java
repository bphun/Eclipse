import javax.swing.JOptionPane;
import kareltherobot.*;


public class MakeADiamond  implements Directions { 

	public static void main(String[] args) { 
		MakeADiamond diamond = new MakeADiamond(); 

		String sideLengthPrompt = JOptionPane.showInputDialog(null, "How large of a side do you want?");
		int sideLength = Integer.parseInt(sideLengthPrompt);	

		World.setDelay(0);
		World.setVisible(true);
				
		diamond.drawDiamond(sideLength);
	}

	private void drawDiamond(int sideLength) {
		Robot robot;
		
		World.setSize(sideLength * 2, sideLength *  2);
		robot = new Robot(1, sideLength, North, infinity);

		final int beeperCount = sideLength * 4;
		System.out.print("Beepers to Draw: " + beeperCount);
		
		int sideCount = 0;
		int currentBeeper;
		
		while (sideCount <= 3) {
			
			System.out.println("Drawing Side: " + sideCount);

			currentBeeper = 0;
			while (currentBeeper < (sideLength - 1)) {
				
				int remainingBeepers = beeperCount - currentBeeper;
				System.out.println("Remaining Beepers: " + remainingBeepers);
						
				robot.putBeeper();
				robot.move();
				robot.turnLeft();
				robot.move();
				turnRight(robot);	

				currentBeeper++;
			}
			turnRight(robot);
			sideCount++;
		}
	}	
	
	void turnRight(Robot robot) {
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}
	
}			
		
//		final int beeperCount = sideLength * 4;
//		int currentBeeper = 0;
//		
//		for (int sideCount = 0; sideCount <= 3; sideCount++) {
//			
//			System.out.println("Side Count: " + sideCount);
//			
//			switch (sideCount) {
//				case 0:
//					while (currentBeeper <= beeperCount / 4 - 1) {
//						if (currentBeeper == 0) {
//							robot.putBeeper();
//						} else if (currentBeeper > 0) {
//							robot.move();
//							robot.turnLeft();
//							robot.move();
//							robot.putBeeper();
//							turnRight(robot);	
//						}
//						currentBeeper++;					
//					}
//			
//				case 1:
//					while (currentBeeper <= beeperCount / 4 - 1) {
//						if (currentBeeper != 4) {
//							robot.move();        
//							turnRight(robot);
//							robot.move();
//							robot.putBeeper();
//							robot.turnLeft();
//						} else {
//							turnAround(robot);
//						}
//						currentBeeper++;  	
//					}
//			
//				case 2:
//					while (currentBeeper <= beeperCount / 4 - 1) {
//						if (currentBeeper != 4) {
//						robot.move();
//						robot.turnLeft();
//						robot.move();
//						robot.putBeeper();
//						turnRight(robot);	
//						}
//						currentBeeper++;	
//					}
//
//				case 3:
//					while (currentBeeper <= beeperCount / 4 - 1) {
//						if (currentBeeper != 4) {
//							robot.move();
//							turnRight(robot);
//							robot.move();
//							robot.putBeeper();
//							robot.turnLeft();	
//						}
//						currentBeeper++;	
//					}
//				default:
//					break;	
//			}
//		}

