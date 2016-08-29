import java.util.*;
import javax.swing.JOptionPane;
import kareltherobot.*;

public class RoomCleaner implements Directions {

	Robot robot;
	ArrayList<Beeper> beeperArray = new ArrayList<Beeper>();
	int roomLength = 0;
	int roomHeight = 0;
	int roomArea;
	int totalBeepers;
	int largestBeeperPile;

	public static void main(String[] args) {
		RoomCleaner roomCleaner = new RoomCleaner();

		roomCleaner.getInfo();
		roomCleaner.cleanRoom();
		roomCleaner.printResults();
	}

	private void getInfo() {
		// this method acquires the starting street, avenue and direction
		// of the robot from the user.  Also it inputs the name of the world
		// file.  It then opens the world file and creates the robot

		String wrldName = "basicRoom.wld";
		World.setVisible(true);	
		World.setDelay(0);
		World.readWorld(wrldName);		
	}

	private void cleanRoom() {
		robot = new Robot(7,7, East, infinity);
		//		String robotXPositionStr = JOptionPane.showInputDialog(null, "What X coordinate do you want to place the robot on");
		//		int robotXPosition = Integer.parseInt(robotXPositionStr);
		//
		//		String robotYPositionStr = JOptionPane.showInputDialog(null, "What Y coordinate do you want to place the robot on");
		//		int robotYPosition = Integer.parseInt(robotYPositionStr);
		//
		//		robot = new Robot(robotYPosition, robotXPosition, North, infinity);

		int roomArea = getRoomArea();
		System.out.print("Room Area: " + roomArea + "\n");
		
		while (true) {
			if (robot.frontIsClear()) {
				robot.move();
				pickBeeper();
			} else if ((!robot.frontIsClear()) && (robot.facingEast())) {
				if (leftIsClear()) {
					slideLeft();
					turnAround();
					pickBeeper();	
				} else {
					break;
				}
			} else if ((!robot.frontIsClear()) && (robot.facingWest())) {
				slideRight();
				turnAround();
				pickBeeper();
			} else if ((!robot.frontIsClear() && (robot.facingNorth()))) {
				robot.turnLeft();
			} else if ((robot.facingNorth()) && (!rightIsClear())) {
				break;
			}
		}	

		goToBottomLeftCorner();
		faceEast();
		while (true) {
			if (robot.frontIsClear()) {
				robot.move();
				sortBeeperArray();
			} else if ((!robot.frontIsClear()) && (robot.facingEast())) {
				if (leftIsClear()) {
					slideLeft();
					turnAround();
					sortBeeperArray();
				} else {
					break;
				}
			} else if ((!robot.frontIsClear()) && (robot.facingWest())) {
				slideRight();
				turnAround();
				sortBeeperArray();
			} else if ((!robot.frontIsClear() && (robot.facingNorth()))) {
				robot.turnLeft();
			} else if ((robot.facingNorth()) && (!rightIsClear())) {
				break;
			}
		}	

		for (Beeper b:beeperArray) {
			System.out.println(b);
		}
		System.out.print("Beeper array size: " + beeperArray.size() + "\n");
	}

	private void printResults() {
		// A bunch of System.out.prints go here
		System.out.print("The area of the room is: " + roomArea +"\n");
		System.out.print("The total number of beeper piles picked up was: " + beeperArray.size() + "\n");	// Should be 9
		System.out.print("Karel picked up a total of " + totalBeepers + " beepers" + "\n");
	}

	private void sortBeeperArray() {

		for (int i = 0; i < beeperArray.size(); i++) {
			int currentXPos = robot.avenue();
			int currentYPos = robot.street();

			if ((currentXPos == beeperArray.get(i).beeperXCord) && (currentYPos == beeperArray.get(i).beeperYCord)) {
				beeperArray.get(i).beeperPileSize++;
			}
		}

	}

	private void pickBeeper() {

		int beeperXPos = 0;
		int beeperYPos = 0;

		Beeper beeper;

		while (robot.nextToABeeper()) {
			int beeperPileSize = 0;
			beeperXPos = robot.avenue();
			beeperYPos = robot.street();

			robot.pickBeeper();
			beeperPileSize = 1;
			totalBeepers++;
			beeper = new Beeper(beeperPileSize, beeperXPos, beeperYPos);	
			beeperArray.add(beeper);
		}
	}
	private int getRoomArea() {
		int initialXPos;
		int initialYPos;

		System.out.print("Determing room's area" + "\n");

		if (!robot.facingEast()) {
			faceEast();			
		}
		goToBottomLeftCorner();
		initialXPos = robot.street();
		initialYPos = robot.avenue();

		while (true) {

			if (robot.facingEast()) {
				roomLength++;
			} else if (robot.facingNorth()) {
				roomHeight++;
			}

			if (robot.frontIsClear()) {
				robot.move();
			} else if ((!robot.frontIsClear()) && (robot.facingEast())) {
				robot.turnLeft();	
			} else if ((!robot.frontIsClear()) && (robot.facingWest())) {
				robot.turnLeft();
			} else if ((!robot.frontIsClear()) && (robot.facingNorth())) {
				robot.turnLeft();
			} else if ((!robot.frontIsClear()) && (robot.facingSouth())) {
				robot.turnLeft();
			}

			if ((robot.street() == initialXPos) && (robot.avenue() == initialYPos)) {				
				break;
			}
		}
		faceEast();
		System.out.print("Room height: " + roomHeight + " Room length: " + roomLength + "\n");

		roomArea = roomLength * roomHeight;

		return roomArea;
	}
	private void goToBottomLeftCorner() {

		faceEast();
		while (rearIsClear()) {			
			slideBackward();
		}
		faceEast();
		while (rightIsClear()) {
			slideRight();
		}

	}
	private boolean rearIsClear() {

		boolean rearIsClear = false;

		turnAround();

		if (robot.frontIsClear()) {
			rearIsClear = true;
		}
		turnAround();

		return rearIsClear;
	}
	private boolean leftIsClear() {

		boolean leftIsClear = false;

		robot.turnLeft();
		if (robot.frontIsClear()) {
			leftIsClear = true;
		}
		turnRight();

		return leftIsClear;
	}
	private boolean rightIsClear() {
		boolean rightIsClear = false;

		turnRight();
		if (robot.frontIsClear()) {
			rightIsClear = true;
		}
		robot.turnLeft();

		return rightIsClear;
	}
	private void slideBackward() {
		turnAround();
		robot.move();
		turnAround();
	}
	private void faceEast() {
		if (robot.facingNorth()) {
			turnRight();
		} else if (robot.facingSouth()) {
			robot.turnLeft();
		} else if (robot.facingWest()) {
			turnAround();
		}
	}
	private void faceNorth() {
		if (robot.facingEast()) {
			robot.turnLeft();
		} else if (robot.facingSouth()) {
			robot.turnLeft();
			robot.turnLeft();
		} else if (robot.facingWest()) {
			turnRight();
		}
	}
	private void turnRight() {
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}

	private void turnAround() {
		robot.turnLeft();
		robot.turnLeft();
	}

	private void slideLeft() {
		robot.turnLeft();
		robot.move();
		turnRight();
	}

	private void slideRight() {
		turnRight();
		robot.move();
		robot.turnLeft();
	}	
}

class Beeper {

	int beeperXCord;
	int beeperYCord;
	int beeperPileSize;

	Beeper(int BeeperS, int beeperX, int beeperY) {
		beeperXCord = beeperX;
		beeperYCord = beeperY;
		beeperPileSize = BeeperS;
	}

}
