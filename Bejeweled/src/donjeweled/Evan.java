package donjeweled;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Evan extends Don {

	static Image image;
	
	public Evan() {
		super(4);
	}

	@Override
	protected void openImage() {
		// copied from Panel class
		if (image != null) { return; }
		try {
 			URL url = getClass().getResource("res/images/evan.jpg");
			image = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening evan.jpg");
			e.printStackTrace();
		}
		img = image;
	}

}
