import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JFrame;


public class PlatformerLauncher {

	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();
		Map<String,String> environMap= System.getenv();
		System.out.println(environMap.keySet());
		System.setProperty("sun.java2d.opengl", "true");
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();      
		PlatformerPanel mop = new PlatformerPanel(d);
		gameFrame.add(mop);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
