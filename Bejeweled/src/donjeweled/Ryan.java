package donjeweled;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Ryan extends Don {

	static Image image;
	
	public Ryan() {
		super(3);
	}

	@Override
	protected void openImage() {
		// copied from Panel class
		if (image != null) { return; }
		try {
 			URL url = getClass().getResource("res/images/ryan.jpg");
			image = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening ryan.jpg");
			e.printStackTrace();
		}
		img = image;
	}

}
