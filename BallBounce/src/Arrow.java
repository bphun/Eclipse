import java.awt.*;

public class Arrow {

	private int xStart, yStart;
	private int mouseX, mouseY;

	public Arrow(int xStart, int yStart, int mouseX, int mouseY) {
		this.xStart = xStart;
		this.yStart = yStart;

	}

	public void draw(Graphics2D g) {
		// float length = (float)Math.sqrt(Math.pow(xStart - mouseX, 2) + Math.pow(yStart - mouseY, 2));

		// int xEnd = (int)(length * Math.cos(theta));

		// g.setColor(Color.RED);
		// g.drawLine(xStart, yStart, xEnd, yEnd);
		// g.setColor(Color.BLACK);
		
	}

	public void setMouseX(int x) {
		this.mouseX = x;
	}

	public void setMouseY(int y) {
		this.mouseY = y;
	}

}
