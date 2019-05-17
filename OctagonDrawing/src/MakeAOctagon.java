import java.util.Scanner;

import kareltherobot.Directions;
import kareltherobot.Robot;
import kareltherobot.World;


public class MakeAOctagon  implements Directions{
	Robot bobbie; // = new Robot(20,20,West,200);
	public static void main(String[] args) {
		MakeAOctagon temp = new MakeAOctagon();

		temp.prompt();
		
		
		
	}
	
	private void prompt() {

		// Ask the user for the diamond size
		System.out.println("Hey dude, I see you want to make an octagon.  Give me the deets: ");
		Scanner keyboard  = new Scanner(System.in);
		int beepersOnSide = keyboard.nextInt();
		//System.out.println(beepersOnSide);
		World.setVisible(true);
		World.setDelay(1);
		World.setSize(30, 30);

		diamond(beepersOnSide);
		bobbie= new Robot(15,25,West,200);

		//side 1 
		int count1 = 2;
		for (count1=2; count1 < beepersOnSide; ++ count1)
		{bobbie.putBeeper();
		Right();
		bobbie.move();
		bobbie.turnLeft();
		bobbie.move();
		}

		bobbie.facingWest();
		//side 2 
		int count2=1;
		for (count2=1; count2< beepersOnSide; ++count2 )
		{
			bobbie.putBeeper();
			bobbie.move();
		}
		//side 3 
		int count3=1;
		for (count3=1; count3 < beepersOnSide ; count3++ )
		{
			bobbie.putBeeper();
			bobbie.turnLeft();
			bobbie.move();
			Right();
			bobbie.move();
		}
		//side 4 
		bobbie.turnLeft();
		int count4=1;
		for (count4=1; count4< beepersOnSide; ++count4 )
		{
			bobbie.putBeeper();
			bobbie.move();
		}
		//side 5
		int count5=1;
		for (count5=1; count5 < beepersOnSide ; count5++ )
		{
			bobbie.putBeeper();
			bobbie.turnLeft();
			bobbie.move();
			Right();
			bobbie.move();
		}

		bobbie.turnLeft();
		//side 6
		int count6=1;
		for (count6=1; count6< beepersOnSide; ++count6 )
		{
			bobbie.putBeeper();
			bobbie.move();
		}
		//side 7 
		int count7=1;
		for (count7=1; count7 < beepersOnSide ; count7++ )
		{
			bobbie.putBeeper();
			bobbie.turnLeft();
			bobbie.move();
			Right();
			bobbie.move();
		}

		bobbie.turnLeft();
		//side 8 
		int count8=0 ;
		for (count8=0;count8 < beepersOnSide; count8++)
		{bobbie.putBeeper();
		bobbie.move();

		}

		bobbie.turnLeft();
		bobbie.move();
		bobbie.facingWest();
		bobbie.move();

		
		}
	






	private void Right()
	{
		bobbie.turnLeft();bobbie.turnLeft();bobbie.turnLeft();
	}




	private void diamond(int beepersOnSide) {
		// make the robot street 1, middle of diamond (corner)


	}

	private void drawSide(Robot r, int beepersOnSide) {

	}

}
