import java.util.Scanner;

import javax.swing.JOptionPane;

import kareltherobot.*;


public class Driver implements Directions{

	Robot robot ;
	/*
	 * @param args
	 */
	public static void main(String[] args) {
		Driver d = new Driver();
		d.getInfo();
		d.cleanRoom();
		d.printResults();
	}

	private void getInfo() {
		// this method acquires the starting street, avenue and direction
		// of the robot from the user.  Also it inputs the name of the world
		// file.  It then opens the world file and creates the robot
		
		//	TODO:	Detect the location and size of the room and place Karel inside of the room.
		
		
		String wrldName = "basicRoom.wld";
		World.readWorld(wrldName);
	}
	
	private void cleanRoom() {

//		String robotXPositionStr = JOptionPane.showInputDialog(null, "What X coordinate do you want to place the robot on");
//		int robotXPosition = Integer.parseInt(robotXPositionStr);
//		
//		String robotYPositionStr = JOptionPane.showInputDialog(null, "What Y coordiante do you wnat to place the robot on");
//		int robotYPosition = Integer.parseInt(robotYPositionStr);
//		
//		robot = new Robot(robotYPosition, robotXPosition, North, infinity);
		
	}
	
	private void printResults() {
		// A bunch of System.out.prints go here
		System.out.println("The biggest pile was ");
	}
	
	private void scanRoomForObstacles() {
		World.setVisible(true);	
		robot = new Robot(1,1,East, 0);
		
//		for (int streets = 0; streets < World.numberOfStreets(); streets++) {
//			for (int avenues = 0; avenues < World.numberOfAvenues(); avenues++) {
//				robot.move();
//			}
//			
//		}
		
	}
}