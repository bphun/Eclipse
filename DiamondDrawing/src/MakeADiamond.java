import javax.swing.JOptionPane;
import kareltherobot.*;

public class MakeADiamond  implements Directions {
    
    public static void main(String[] args) {
        MakeADiamond diamond = new MakeADiamond();
        
        //	Prompt the user to input their desired diamond side length then convert it from a 'String' to an 'int'
        String sideLengthPrompt = JOptionPane.showInputDialog(null, "How large of a side do you want?");
        int sideLength = Integer.parseInt(sideLengthPrompt);
        
        //	Set the delay to 0 so that Karel draws faster, display the world
        World.setDelay(0);
        World.setVisible(true);
        
        //	Draw the diamond
        diamond.drawDiamond(sideLength);
    }
    
    //	MARK: Diamond drawing method
    
    private void drawDiamond(int sideLength) {
        Robot robot;
        
        //	Set the scale the world so that it fits the diamond
        World.setSize(sideLength * 2, sideLength *  2);
        
        //	Initialize the robot at the bottom of the y-axis and center of the x-axis, facing north, remove the beeper count limit
        robot = new Robot(1, sideLength, North, infinity);
        
        final int beeperCount = sideLength * 4;
        System.out.print("Total beepers to Draw: " + beeperCount);
        
        //	Declare 'sideCount' variable to keep track of the current side being drawed, use 'currentBeeper' variable to keep track of what beeper is currently being drawed
        int sideCount = 0;
        int currentBeeper;
        
        //	Draw the desired # of beepers on all 4 sides
        while (sideCount <= 3) {
            
            System.out.println("Currently drawing Side: " + sideCount);
            
            //	Reset the current beeper count so that we can draw the first beeper of the diamond's side
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
    
    //	MARK: Karel helper methods
    
    void turnRight(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
    }
}
