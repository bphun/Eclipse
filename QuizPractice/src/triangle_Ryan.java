
import javax.swing.JOptionPane;

import kareltherobot.Directions; 
import kareltherobot.Robot; 
import kareltherobot.World; 


public class triangle_Ryan  implements Directions{ 


	public static void main(String[] args) { 
		String num = JOptionPane.showInputDialog(null,  "Pick a height");
		int height = Integer.parseInt(num);
		double h = height;
		String num1 = JOptionPane.showInputDialog(null,  "Pick a width");
		int width = Integer.parseInt(num1);
		double w = width;
		double slope= h/w;
		int temp=0;
		Robot r= new Robot(1,1,East,infinity); 
		World.setSize(height+3, width+3);
		World.setVisible(true);
		World.setDelay(1);

		while (!r.facingNorth())
			r.turnLeft();
		for (; w>0; w--){
			for (;h>0; h--){
				r.putBeeper();
				r.move();
			}
			h=height-slope*temp;
			r.turnLeft();
			r.turnLeft();
			for (int stepBack=0; stepBack<h; stepBack++)
				r.move();
			r.turnLeft();
			r.move();
			r.turnLeft();
			temp= temp +1;
			h= height-slope*temp;
		}
		r.move();
		// int h and w; make double for h and w, make slope m from top and bottom point, subtract slope from h, going up and down.


	}



}

// Just fyi, I didn’t incorporate step three, so it’s not ‘correct’ per say


