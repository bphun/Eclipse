import java.util.Scanner;

import javax.swing.JOptionPane;

import kareltherobot.*;


public class Driver implements Directions{

	Robot robot ;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// LEAVE THIS ALONE!!!!!!
		Driver d = new Driver();
		d.getInfo();
		d.cleanRoom();
		d.printResults();
	}

	private void printResults() {
		// A bunch of System.out.prints go here
		System.out.println("The biggest pile was ");
	}

	private void cleanRoom() {
		// all the code that cleans and counts goes here
		robot.move();// obviously, lots more here
	}

	private void getInfo() {
		// this method acquires the starting street, avenue and direction
		// of the robot from the user.  Also it inputs the name of the world
		// file.  It then opens the world file and creates the robot
		String wrldName = "basicRoom.wld";
		
		
		World.readWorld(wrldName);
		World.setVisible(true);
		
	}

}
