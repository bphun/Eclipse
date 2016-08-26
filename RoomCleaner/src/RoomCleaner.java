import java.util.Scanner;
import javax.swing.JOptionPane;
import kareltherobot.*;

public class RoomCleaner implements Directions {

	Robot robot;
	
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
		
		//	TODO:	Detect the location and size of the room and place Karel inside of the room.
				
		String wrldName = "basicRoom.wld";
		World.setVisible(true);	
		World.setDelay(20);
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
				robot.pickBeeper();
			} else if ((!robot.frontIsClear()) && (robot.facingEast())) {
				slideLeft();
				robot.pickBeeper();
				turnAround();
			} else if ((!robot.frontIsClear()) && (robot.facingWest())) {
				slideRight();
				robot.pickBeeper();
				turnAround();
			}
		}	
	
	}
	
	private void printResults() {
		// A bunch of System.out.prints go here
		System.out.println("The biggest pile was: ");
	}
	
	private int getRoomArea() {
		int roomLength = 0;
		int roomHeight = 0;
		int roomArea;
		
		int initialXPos = robot.street() - 1;
		int initialYPos = robot.avenue() - 1;
		
		while (robot.frontIsClear()) {
			if (robot.facingEast()) {
				roomLength++;
			} else if (robot.facingNorth()) {
				roomHeight++;
			}
			robot.move();
			if (!robot.frontIsClear()) {
				robot.turnLeft();
			}
			
			if ((robot.street() - 1 == initialXPos) && (robot.avenue() - 1 == initialYPos)) {
				break;
			}
			
		}
		
		roomArea = roomLength * roomHeight;
				
		return roomArea;
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

class Wall {
	
	int xCord, yCord;
	
	Wall(int xCordI, int yCordI) {
		
		xCord = xCordI;
		yCord = yCordI;
	}
	
}
