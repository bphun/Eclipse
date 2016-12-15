package donjeweled;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Thiel extends Don {

	static Image image;
	
	public Thiel() {
		super(2);
	}

	@Override
	protected void openImage() {
		// copied from Panel class
		if (image != null) { return; }
		try {
 			URL url = getClass().getResource("res/images/eric-thiel-photo_248x248.jpg");
			image = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening eric-thiel-photo_248x248.jpg");
			e.printStackTrace();
		}
		img = image;
	}

}
