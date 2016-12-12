package donjeweled;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Simms extends Don {

	static Image image;
	
	public Simms() {
		super(0);
	}

	@Override
	protected void openImage() {
		// copied from Panel class
		if (image != null) { return; }
		try {
 			URL url = getClass().getResource("res/images/simms.jpg");
			image = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening the simms.jpg");
			e.printStackTrace();
		}
		img = image;
	}

}
