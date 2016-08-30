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
//		String wrldName = JOptionPane.showInputDialog(null, "What is the name of the world file you want to load?");
		World.setVisible(true);	
		World.setDelay(0);
		World.readWorld(wrldName);		
	}

	private void cleanRoom() {
//		robot = new Robot(7,7, East, infinity);
		
		String robotXPositionStr = JOptionPane.showInputDialog(null, "What X coordinate do you want to place the robot on");
		int robotXPosition = Integer.parseInt(robotXPositionStr);

		String robotYPositionStr = JOptionPane.showInputDialog(null, "What Y coordinate do you want to place the robot on");
		int robotYPosition = Integer.parseInt(robotYPositionStr);

		String robotStartingDirection = JOptionPane.showInputDialog("What direction do you want karel to face?" + "\n" + "North" + "\n" + "East" + "\n" + "South" + "\n" + "West");
		Direction startingDirection = null;
		
		if (robotStartingDirection.equalsIgnoreCase("north")) {
			startingDirection = North;
		} else if (robotStartingDirection.equalsIgnoreCase("east")) {
			startingDirection = East;
		} else if (robotStartingDirection.equalsIgnoreCase("south")) {
			startingDirection = South;
		} else if (robotStartingDirection.equalsIgnoreCase("west")) {
			startingDirection = West;
		}
		
		robot = new Robot(robotYPosition, robotXPosition, startingDirection, infinity);

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
	}
	private void printResults() {
		// A bunch of System.out.prints go here
		int largestBeeperPileSize = getLargestBeeperPileSize();
		double averageBeeperPileSize = calculateAverageBeeperPileSize();
		double percentDirty = getPercentDirty();
		ArrayList<Integer> distanceFromLargestBeeperPile = getDistanceFromLargestBeeperPile();
		
		System.out.print("The area of the room is: " + roomArea +"\n");
		System.out.print("Karel picked up a total of " + totalBeepers + " beepers" + "\n");
		System.out.print("The total number of beeper piles picked up was: " + beeperArray.size() + "\n");
		System.out.print("The largest beeper pile was: " + largestBeeperPileSize + "\n");
		System.out.print("The X coordinate of the largest beeper pile was: " + distanceFromLargestBeeperPile.get(0) + "\n" + "The Y coordiante of the largest beeper pile was: " + distanceFromLargestBeeperPile.get(1) + "\n");
		System.out.print("The average beeper pile size is: " + averageBeeperPileSize + "\n");
		System.out.print("The percent dirty is: " + percentDirty + "%" + "\n");
	}
	private int getLargestBeeperPileSize() {
	    int largestPile = beeperArray.get(0).beeperPileSize;

	    for(int i=0; i < beeperArray.size(); i++){
	        if(beeperArray.get(i).beeperPileSize > largestPile){
	        	largestPile = beeperArray.get(i).beeperPileSize;
	        }

	    }
	    return (largestPile);
	}	
	private ArrayList<Integer> getDistanceFromLargestBeeperPile() {
	    Beeper largestPile = beeperArray.get(0);

	    for(int i=0; i < beeperArray.size(); i++){
	        if(beeperArray.get(i).beeperPileSize > largestPile.beeperPileSize){
	        	largestPile = beeperArray.get(i);
	        }

	    }
	    ArrayList<Integer> largestPileCoordinate = new ArrayList<Integer>();
	    
	    largestPileCoordinate.add(largestPile.beeperXCord);
	    largestPileCoordinate.add(largestPile.beeperYCord);
	    
	    return largestPileCoordinate;
	}
	private double calculateAverageBeeperPileSize() {
		Integer sum = 0;
		if(!beeperArray.isEmpty()) {
			for (int i = 0; i < beeperArray.size(); i++) {
				sum += beeperArray.get(i).beeperPileSize;
			}
			return sum.doubleValue() / beeperArray.size();
		}
		return sum;
	}
	private double getPercentDirty() {		
		int beeperArraySize = beeperArray.size();
		double percentDirty = ((double)beeperArraySize / roomArea) * 10;
		return percentDirty;
	}
	private void pickBeeper() {

		int beeperXPos = 0;
		int beeperYPos = 0;
		int beeperPileSize = 0;

		Beeper beeper;

		while (robot.nextToABeeper()) {
			beeperXPos = robot.avenue();
			beeperYPos = robot.street();

			robot.pickBeeper();
			beeperPileSize++;
			totalBeepers++;
		}
		
		if (beeperPileSize != 0) {
			beeper = new Beeper(beeperPileSize, beeperXPos, beeperYPos);
			System.out.println("Picked up a pile of " + beeperPileSize + " beepers");
			beeperArray.add(beeper);
			beeperPileSize = 0;	
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
