package donjeweled;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class DrDre extends Don {

	static Image image;
	
	public DrDre() {
		super(1);
	}

	@Override
	protected void openImage() {
		// copied from Panel class
		if (image != null) { return; }
		try {
			URL url = getClass().getResource("res/images/DrDre.jpg");
			image = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening the DrDre.jpg");
			e.printStackTrace();
		}
		img = image;
	}

}
