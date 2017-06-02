import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

public class RandomNumberGenerator {

	public static void main(String[] args) {
		int r = (int)(Math.random() * 255);
		int g = (int)(Math.random() * 255);
		int b = (int)(Math.random() * 255);
		
		System.out.println("R: " + r + ", G: " + g + ", B: " + b);
		
		JPanel panel = new JPanel();
		JFrame frame = new JFrame("Random Color Generator");	
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setBackground(new Color(r, g, b));	
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
