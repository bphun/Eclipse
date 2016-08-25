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
		World.setDelay(5);
		World.readWorld(wrldName);		
	}
	
	private void cleanRoom() {
		scanWorldForRoom();
//		String robotXPositionStr = JOptionPane.showInputDialog(null, "What X coordinate do you want to place the robot on");
//		int robotXPosition = Integer.parseInt(robotXPositionStr);
//		
//		String robotYPositionStr = JOptionPane.showInputDialog(null, "What Y coordinate do you want to place the robot on");
//		int robotYPosition = Integer.parseInt(robotYPositionStr);
//		
//		robot = new Robot(robotYPosition, robotXPosition, North, infinity);
		
	}
	
	private void printResults() {
		// A bunch of System.out.prints go here
		System.out.println("The biggest pile was: ");
	}
	
	
	private void scanWorldForRoom() {
		robot = new Robot(1,1,North, 1);
		for (int avenues = World.numberOfAvenues() - 1; avenues > 0; avenues--) {
			for (int streets = World.numberOfStreets() - 1; streets > 0; streets--) {
				slideRight();
				checkForObstacle();
			}
			robot.move();
			checkForObstacle();
			for (int streets = World.numberOfStreets() - 1; streets > 0; streets--) {
				slideLeft();
				checkForObstacle();
			}
			robot.move();
			checkForObstacle();
		}
		
//		for (int avenues = World.numberOfAvenues() - 1; avenues > 0; avenues--) {
//			for (int streets = World.numberOfStreets() - 1; streets > 0; streets--) {
//				robot.move();
//				robot.turnLeft();
//				turnRight(robot);
//			}
//			slideLeft(robot);
//			turnAround(robot);
//			for (int streets = World.numberOfStreets() - 1; streets > 0; streets--) {
//				robot.move();
//			}
//			slideRight(robot);
//			turnAround(robot);
//		}
		
	}
	
	private void checkForObstacle() {
		int currentXPos = robot.avenue() - 1;
		int currentYPos = robot.street() - 1;
		
		Wall wall;
		Wall[] wallArray = new Wall[World.numberOfAvenues() * World.numberOfStreets()];
		
		int currentElement = 0;
		
		if (robot.facingNorth()) {
			if (!robot.frontIsClear()) {
				wall = new Wall(currentXPos, currentYPos);
				wallArray[currentElement] = wall;
				currentElement++;
			}			
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

class Wall {
	
	int xCord, yCord;
	
	Wall(int xCordI, int yCordI) {
		
		xCord = xCordI;
		yCord = yCordI;
	}
	
}
