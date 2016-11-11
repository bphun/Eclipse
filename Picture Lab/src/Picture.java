import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments 
	 */
	public Picture () {
		/* not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor 
		 */
		super();  
	}

	/**
	 * Constructor that takes a file name and creates the picture 
	 * @param fileName the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * @param height the height of the desired picture
	 * @param width the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width,height);
	}

	/**
	 * Constructor that takes a picture and creates a 
	 * copy of that picture
	 * @param copyPicture the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * @param image the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
	}

	/****************************MARK: Methods*******************************/
	/**
	 * Method to return a string with information about this picture.
	 * @return a string with information about the picture such as fileName,
	 * height and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + 
				" height " + getHeight() 
				+ " width " + getWidth();
		return output;
	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}

	/** Method to set the red to 0 */
	public void zeroRed() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
			}
		}
	}

	/* Method to set the green to 0 */
	public void zeroGreen() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setGreen(0);
			}
		}
	}

	/* Method to invert all of the colors in an 
	 * image by subtracting the current color 
	 * component from 255 and setting that to 
	 * the new color component
	 */
	public void negateColors() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(255 - pixelObj.getRed());
				pixelObj.setGreen(255 - pixelObj.getGreen());
				pixelObj.setBlue(255 - pixelObj.getBlue());

			}
		}
	}

	/* Method to set an image to grayscale by averaging 
	 * all color components and setting that value
	 *  to all the components 
	 */
	public void setGrayScale() {
		Pixel[][] pixels = this.getPixels2D();
		int average = 0;
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				average += pixelObj.getRed();
				average += pixelObj.getGreen();
				average += pixelObj.getBlue();
				average /= 3;
				pixelObj.setRed(average);
				pixelObj.setGreen(average);
				pixelObj.setBlue(average);
			}
		}
	}


	/** Method that mirrors the picture around a 
	 * vertical mirror in the center of the picture
	 * from left to right */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		for (int r = 0; r < pixels.length / 2; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				leftPixel = pixels[r][c];
				rightPixel = pixels[r][(pixels.length / 2) + c];
				leftPixel.setColor(rightPixel.getColor());
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {// This method makes a mirror image of a section of this picture
		// If this picture is of the temple, it mirror images the top.
		// what if this picture is of a different image?
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {
				leftPixel = pixels[row][col];      
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	/** copy from the passed fromPic to the
	 * specified startRow and startCol in the
	 * current picture
	 * @param fromPic the picture to copy from
	 * @param startRow the start row to copy to
	 * @param startCol the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length && toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length && toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}   
	}

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1,0,0);
		this.copy(flower2,100,0);
		this.copy(flower1,200,0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue,300,0);
		this.copy(flower1,400,0);
		this.copy(flower2,500,0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}


	/** Method to show large changes in color 
	 * This method traverses this picture and changes to pixels to 
	 * black and white, depending on the color to each pixel's right.
	 * If the color change is large, then the pixel on the left is set to 
	 * black, otherwise, if the color is close, then the pixel is set to 
	 * white. The result is an image with black pixels where it detected 
	 * a significant change in color.
	 * 
	 * @param edgeDist the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;// this pixel will always be the one to 
		// the left of rightPixel.  If this Pixel
		// is far enough away (based on edgeDist), then
		// leftPixel is set to Color black, else, white

		Pixel rightPixel = null;// this Pixel doesn't change value, it is just
		// used as a reference for comparing with leftPixel

		Pixel[][] pixels = this.getPixels2D();// gets the 2D array of Pixel

		// Big hint, the Pixel class has a method called colorDistance(Color) which
		// returns the distance the input Color is from this Pixel's Color
		for (Pixel[] rowArray : pixels) {
			for (int c = 1; c < rowArray.length; c++) {
				leftPixel = rowArray[c - 1];
				rightPixel = rowArray[c];

				if (leftPixel.colorDistance(rightPixel.getColor()) > edgeDist) {
					leftPixel.setRed(0);
					leftPixel.setGreen(0);
					leftPixel.setBlue(0);
				} else {
					leftPixel.setRed(255);
					leftPixel.setGreen(255);
					leftPixel.setBlue(255);
				}
			}
		}  
	}

	/*	Method that reduce red, green, and blue components
	 *  to make the image seem as if the water has been removed
	 */
	public void fixUnderwater() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixel : rowArray) {
				pixel.setRed(pixel.getRed() - 10);
				pixel.setGreen(pixel.getGreen() - 130);
				pixel.setBlue(pixel.getBlue() - 130);
			}
		}	
	}
	
	/** Method that gets an image with a message and imposes it on another image
	 * @param the image containing the message with a white background an black text
	 */
	public void encodeAndDecode(Picture message) {
		Pixel[][] pixels = this.getPixels2D();
		Pixel[][] pixelsMsg = message.getPixels2D();
		
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				if (pixels[r][c].getRed() % 2 > 0) {
					while (pixels[r][c].getRed() % 2 > 0) {
						pixels[r][c].setRed(pixels[r][c].getRed() - 1);
					}
				}
				
				if ((pixelsMsg[r][c].getX() == pixels[r][c].getX()) && pixelsMsg[r][c].getY() == pixels[r][c].getY()) {
					if (pixelsMsg[r][c].isBlack()) {
						pixels[r][c].setRed(0);
						pixels[r][c].setGreen(0);
						pixels[r][c].setBlue(0);
					}
				}
			}
		}	
	}
	
	/** Method that returns the number of pixels with a value 
	 * over the specified value
	 * @param the red component value you want to detect
	 * @return The number of pixels with a red component greater than the specified value 
	 */
	public int getCountRedOverValue(int value) {
		int total = 0;
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixel : rowArray) {
				if (pixel.getRed() > value) {
					total++;
				}
			}
		}
		return total;
	}
	
	public void setRedToHalfValueInTopHalf() {
		Pixel[][] pixels = this.getPixels2D();
		for (int r = (pixels.length - 1) / 2; r > 0; r--) {
			for (int c = 0; c < pixels[r].length; c++) {
				pixels[r][c].setRed(pixels[r][c].getRed() / 2);
			}
		}
	}
	
	public void clearBlueOverValue(int value) {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixel : rowArray) {
				if (pixel.getBlue() > value) {
					pixel.setBlue(0);
				}
			}
		}
	}
	
	public Color getAverageForColumn(int col) {
		Color avg = null;
		

		
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			
		}
		
		return avg;
	}
	
	public void applyGreenScreen(Picture background) {
		Pixel[][] pixels = this.getPixels2D();
		
//		background.resize(background, pixels.length, pixels[pixels.length].length);
		
		Pixel[][] pixelsBg = background.getPixels2D();
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				if (pixels[r][c].containsNoGreen()) {
					pixels[r][c].setRed(pixelsBg[r][c].getRed());
					pixels[r][c].setGreen(pixelsBg[r][c].getGreen());
					pixels[r][c].setBlue(pixelsBg[r][c].getBlue());

				}
			}
		}
	}

	public void resize(Picture image, int widht, int height) {
		
	}

	/* Main method for testing - each class in Java can have a main 
	 * method 
	 */
	public static void main(String[] args) 
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

} // this } is the end of class Picture, put all new methods before this
