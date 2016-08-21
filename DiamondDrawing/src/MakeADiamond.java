import javax.swing.JOptionPane;
import kareltherobot.*;


public class MakeADiamond  implements Directions { 

	public static void main(String[] args) { 
		MakeADiamond diamond = new MakeADiamond(); 
<<<<<<< HEAD

		String sideLengthPrompt = JOptionPane.showInputDialog(null, "How large of a side do you want?");
		int sideLength = Integer.parseInt(sideLengthPrompt);	

		World.setDelay(0);
		World.setVisible(true);
		
=======
//		World.setDelay(0);
		World.setVisible(true);
		
		String sideLengthPrompt = JOptionPane.showInputDialog(null, "How large of a side do you want?");
		int sideLength = Integer.parseInt(sideLengthPrompt);	
				
>>>>>>> 131462a6a2a19690cf03eaa3fd81484b59e09573
		diamond.drawDiamond(sideLength);
	}

	private void drawDiamond(int sideLength) {
		Robot robot;
<<<<<<< HEAD

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
				
=======
				
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
>>>>>>> 131462a6a2a19690cf03eaa3fd81484b59e09573
				robot.putBeeper();
				robot.move();
				robot.turnLeft();
				robot.move();
<<<<<<< HEAD
				turnRight(robot);	

				currentBeeper++;
			}
			turnRight(robot);
			sideCount++;
		}
		
//		for (int sideCount = 0; sideCount <= 3; sideCount++) {
//			
//			System.out.println("Drawing Side: " + sideCount);
//			
//			for (int currentBeeper = 0;  currentBeeper < (beeperCount / 4) - 1; currentBeeper++) {
//				
//				int remainingBeepers = beeperCount - currentBeeper;
//				System.out.println("Remaining Beepers: " + remainingBeepers);
//				
//				robot.putBeeper();
//				robot.move();
//				robot.turnLeft();
//				robot.move();
//				turnRight(robot);	
//			}
//			turnRight(robot);
//		}			

		
//		if (sideLength > 5) {
//			robot = new Robot(1, sideLength, North, infinity);
//		} else {
//			 robot = new Robot(1,5,North,30);
//		}
		
//		final int beeperCount = sideLength * 4;
//		int currentBeeper = 0;
//		
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
=======
				turnRight(robot);
				
				int remainingBeepers = beeperCount - currentBeeper;
				System.out.println("Remaining Beepers: " + remainingBeepers);
				
			}
			turnRight(robot);
		}			
>>>>>>> 131462a6a2a19690cf03eaa3fd81484b59e09573
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
<<<<<<< HEAD
=======

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
>>>>>>> 131462a6a2a19690cf03eaa3fd81484b59e09573
