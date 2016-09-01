import java.util.*;
import javax.swing.JOptionPane;
import kareltherobot.*;

public class RoomCleaner implements Directions {

	Robot robot;
	ArrayList<Beeper> beeperArray = new ArrayList<Beeper>();	//	ArrayList used to store beeper piles
	int roomLength = 0;
	int roomHeight = 0;
	int roomArea;
	int totalBeepers;
	int largestBeeperPile;

	public static void main(String[] args) {
		RoomCleaner roomCleaner = new RoomCleaner();
		
		//	Ask the user what world file they would like to use"
		roomCleaner.getInfo();
		
		//	Pick up all of the beepers in the room
		roomCleaner.cleanRoom();
		
		//	Print all the needed info (i.e. Room area, total beeper collected, beeper piles collected, largest beeper pile size, distance relative to largest beeper pile, average beeper size, percent 'dirty')
		roomCleaner.printResults();
	}

	//	MARK: Gather the needed world file info
	private void getInfo() {		
		//	Prompt the user for the world file that they would like to use and then load it
		String wrldName = JOptionPane.showInputDialog(null, "What is the name of the world file you want to load?");
		World.readWorld(wrldName);		

		//	Set the delay to 0 so that we don't have to wait a long time for Karel to clean the room
		World.setDelay(0);
	}

	//	MARK: Have Karel clean the room it is in 
	private void cleanRoom() {		
		
		//	Prompt the user to ask them for their starting X coordinate and then convert it from a String to an int so that we can use it later on 
		String robotXPositionStr = JOptionPane.showInputDialog(null, "What X coordinate do you want to place the robot on");
		int robotXPosition = Integer.parseInt(robotXPositionStr);

		//	Prompt the user to ask them for their starting Y coordinate and then convert it from a String to an int so that we can use it later on
		String robotYPositionStr = JOptionPane.showInputDialog(null, "What Y coordinate do you want to place the robot on");
		int robotYPosition = Integer.parseInt(robotYPositionStr);

		//	Prompt the user to ask them for their starting cardinal direction so that we can initialize Karel facing that way
		String robotStartingDirection = JOptionPane.showInputDialog("What direction do you want karel to face?" + "\n" + "North" + "\n" + "East" + "\n" + "South" + "\n" + "West");
		Direction startingDirection = null;
		
		//	Convert the user inputed string directio value to a Direction value so that we can use it when we initialize Karel
		if (robotStartingDirection.equalsIgnoreCase("north")) {
			startingDirection = North;
		} else if (robotStartingDirection.equalsIgnoreCase("east")) {
			startingDirection = East;
		} else if (robotStartingDirection.equalsIgnoreCase("south")) {
			startingDirection = South;
		} else if (robotStartingDirection.equalsIgnoreCase("west")) {
			startingDirection = West;
		}
		
		//	Initialize Karel with the desired X,Y coordinates, and starting direction
		robot = new Robot(robotYPosition, robotXPosition, startingDirection, infinity);

		//	Make the world visible after we have loaded everything
		World.setVisible(true);	

		//	(1). Determine the room's area
		roomArea = getRoomArea();
		
		//	Walk around the room picking up a beeper if there is a beeper there
		while (true) {
			if (robot.frontIsClear()) {
				pickBeeper();	//	This is needed because without it Karel would not pick up any beepers in the bottom left corner
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
	
	//	Print all of the required info about what Karel did in the room
	private void printResults() {
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
	
	//	MARK:	Methods used to calculate the required information about what karel did in the room
	
	//	Method used to get the largest beeper pile out of all of the piles Karel picked up
	private int getLargestBeeperPileSize() {
	    int largestPile = beeperArray.get(0).beeperPileSize;

	    //	Loop through the array checking if the current beeper is larger than the current largestPile 
	    for(int i = 0; i < beeperArray.size(); i++) {
	        if(beeperArray.get(i).beeperPileSize > largestPile){
	        	largestPile = beeperArray.get(i).beeperPileSize;
	        }
	    }
	    return (largestPile);
	}	
	
	//	Method used to return an array containing the (X,Y) coordinate of the largest beeper pile
	private ArrayList<Integer> getDistanceFromLargestBeeperPile() {
	    Beeper largestPile = beeperArray.get(0);

	    for(int i = 0; i < beeperArray.size(); i++){
	        if(beeperArray.get(i).beeperPileSize > largestPile.beeperPileSize) {
	        	largestPile = beeperArray.get(i);
	        }

	    }
	    
	    ArrayList<Integer> largestPileCoordinate = new ArrayList<Integer>();
	    
	    largestPileCoordinate.add(largestPile.beeperXCord);
	    largestPileCoordinate.add(largestPile.beeperYCord);
	    
	    return largestPileCoordinate;
	}
	
	//	Method used to determine the average beeper pile size
	private double calculateAverageBeeperPileSize() {
		Integer sum = 0;
		
		if(!beeperArray.isEmpty()) {
			
			//	Loop through the array adding the current elements pile size to the sum
			for (int i = 0; i < beeperArray.size(); i++) {
				sum += beeperArray.get(i).beeperPileSize;
			}
			//	Calculate the average
			return sum.doubleValue() / beeperArray.size();
		}
		return sum;
	}
	
	//	Calculate the percentage of the room that was covered in beepers
	private double getPercentDirty() {		
		int beeperArraySize = beeperArray.size();
		double percentDirty = ((double) beeperArraySize / roomArea) * 100;
		return percentDirty;
	}
	
	//	Method that is used to determine the rooms area by walking along the rooms perimeter and increasing either the room's height or width depending on Karel's orientation at that time
	private int getRoomArea() {
		int initialXPos;
		int initialYPos;

		//	Make sure that Karel is facing east so that the code below works
		if (!robot.facingEast()) {
			faceEast();			
		}
		
		//	Move karel to the bottom left corner so that we can begin calculating the room's perimeter
		goToBottomLeftCorner();
		
		//	Get Karel's initial (X,Y) coordinates so that we can check if we have walked around the perimeter
		initialXPos = robot.street();
		initialYPos = robot.avenue();

		while (true) {
			
			//	Check Karel's orientation so that we can get the side length
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
	
	//	MARK:	Karel helper methods	

	//	Method used to conditionally pick up beepers 
	private void pickBeeper() {
		int beeperXPos = 0;
		int beeperYPos = 0;
		int beeperPileSize = 0;
		Beeper beeper;

		while (robot.nextToABeeper()) {
			
			//	Get the robots current (X,Y) coordinates so that we can use it to initialize the beeper with the X and Y coordinates
			beeperXPos = robot.avenue();
			beeperYPos = robot.street();

			//	Pick up the beeper, increment the beeper pile size and the total number of beepers picked up 
			robot.pickBeeper();
			beeperPileSize++;
			totalBeepers++;
		}
		
		//	Check if the pile size != 0, this is needed because if we didn't than we would have beepers with a size of 0
		if (beeperPileSize != 0) {
			//	Create a beeper object initialized with the pile size and the (X,Y) coordinates
			beeper = new Beeper(beeperPileSize, beeperXPos, beeperYPos);
			
			//	Add the beeper to the ArrayList
			beeperArray.add(beeper);
			
			//	Reset the beeper pile size so that the next beeper pile has the correct size
			beeperPileSize = 0;	
		}
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
