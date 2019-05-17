import java.awt.Color;

/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.  This is a great lesson for learning 
 * about 2D arrays and the Color class.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
	
	/* Main method for testing.  Every class can have a main
	 * method in Java
	 */
	public static void main(String[] args) {
		/*
		 * You will write the methods that do the following
		 * 
		 */
		testZeroBlue();
		testKeepOnlyBlue();
	    testKeepOnlyRed();
     testKeepOnlyGreen();
		testNegate();
		testGrayscale();
		testEdgeDetection();
		testFixUnderwater();
		testMirrorVertical();
        //	  testMirrorTemple();
		//    testMirrorArms();
		//    testMirrorGull();
		//    testMirrorDiagonal();
		//	  testCollage();
	    testCopy();
		testChromakey();
		testEncodeAndDecode();  // use PNG, GIF or BMP because of compression
		testGetCountRedOverValue(250);
		testSetRedToHalfValueInTopHalf();
		testClearBlueOverValue(180);
//		Color avgColor = testGetAverageForColumn(90);// specified column 
//		System.out.println("Average color: " + avgColor);
	}
	
	/** Method to test zeroBlue */
	public static void testZeroBlue()
	{
		// opens the image so that it can be manipulated
		Picture beach = new Picture("beach.jpg");
		beach.explore();// shows the picture in a windo
		beach.zeroBlue();// runs the zeroBlue method
		
		// shows the current version of the pic in a new window
		beach.explore(); 
	}

	private static void testKeepOnlyBlue() {
		// should get a fairly blue pic
		// this method will look a lot like testZeroBlue method
		Picture beach = new Picture("beach.jpg");
		beach.zeroRed();
		beach.zeroGreen();
		beach.explore();
	}
	
	private static void testKeepOnlyGreen() {
		Picture beach = new Picture("beach.jpg");
		beach.zeroRed();
		beach.zeroBlue();
	}

	private static void testKeepOnlyRed() {
		Picture beach = new Picture("beach.jpg");
		beach.zeroBlue();
		beach.zeroRed();
	}
	
	private static void testNegate() {
		// Turns a Picture into its negative
		// flip all the colors:  if color had red = 30, green = 100, blue = 200
		// Negated color red = 225, green= 155, blue = 55
		Picture beach = new Picture("beach.jpg");
		beach.negateColors();
		beach.explore();
	}
	

	private static void testGrayscale() {
		// converts a color image into grayscale.  There are many algorithms 
		// for this.  The most common is to find the mean of the red, green 
		// and blue components and set each component to that average
		Picture beach = new Picture("beach.jpg");
		beach.setGrayScale();
		beach.explore();
	}

	/** Method to test edgeDetection */
	public static void testEdgeDetection() {
		Picture swan = new Picture("swan.jpg");
		
		// You need to write the edgeDetection method.  The larger the 
		// input value, the farther 
		
		swan.edgeDetection(10);
		swan.explore();
		swan.write("swan outline.jpg");// writes the new picture to a new file
	}
	/** Method to test mirrorVertical */
	public static void testMirrorVertical()
	{
		Picture caterpillar = new Picture("caterpillar.jpg");
		caterpillar.explore();
		// this should take the left-hand half of the picture and reflect it across 
		// the vertical midline.
		caterpillar.mirrorVertical();// need to write this method in the picture class
		caterpillar.explore();
	}

	/** Method to test mirrorTemple */
	public static void testMirrorTemple()
	{
		Picture temple = new Picture("temple.jpg");
		temple.mirrorTemple();// this method creates a mirror image for a section of its
		// pixels.  What would happen if we used a different starting 
		// image?  Is mirrorTemple a useful method in the long run?  How
		// could you change it so that it would be more useful?
		temple.explore();
	}

	/** Method to test the collage method */
	public static void testCollage()
	{
		Picture canvas = new Picture("640x480.jpg");
		canvas.createCollage();// this method has been written in the Picture class
		// how can it be changed so that we could pass in 
		// pictures that could be added to a collage?
		canvas.explore();
	}


	/*so, you have a choice for this one and the methods that follow.  You can write the
	 * code in the methods below or you can add functionality to the picture class.  Sometimes
	 * it makes sense to add it to the class for reusability reasons.  Sometimes it is too unique
	 * a need to add to the class. 
	 */

	// So, you can create a Picture Object and find the average value of 
	// the component in that column
	private static Color testGetAverageForColumn(int col) {
		
		Picture image = new Picture("redMotorcycle.jpg");
		
		Color avg = image.getAverageForColumn(col);
		
		return avg;
	}

   /*
	*so for this one, any pixels that have blue over a certain value are set 
    *to no blue at all.  Or for a different effect, have those pixels set to black.
    */
	private static void testClearBlueOverValue(int value) {
		Picture image = new Picture("water.jpg");
		image.clearBlueOverValue(value);
		image.explore();
	}

   /*
	*goes to each pixel in the top half and cuts the red component in half
	*So, bottom half of pic should look normal
	*/
	private static void testSetRedToHalfValueInTopHalf() {
		Picture image = new Picture("gorge.jpg");
		image.setRedToHalfValueInTopHalf();
		image.explore();
	}
	
   /*
	*displays the number of pixels in the pic that have a red component
	*greater than the specifies int.
	*/
	private static void testGetCountRedOverValue(int value) {
		Picture image = new Picture("gorge.jpg");
		System.out.println("There are a total of " + image.getCountRedOverValue(value) + " pixels with a red component greater than " + value + " in the image");
	}

/*
 * The method below is really cool!!  Use the following approach:
 * go through the entire Picture (the one to carry the message), 
 * and make the red component of every pixel an even number.  
 * To do this, mod by 2 and see if remainder>0.  If so, add or 
 * subtract one (subtracting is safer. Why?) 
 * Then, using a Picture of a message (one color on white background), 
 * any pixel from Picture of message that is not white causes you to 
 * add one to the corresponding pixel's red component of the 
 * encoded picture (the one with all even red components).
 * 
 * Then you can send along the encoded picture to someone else and they should 
 * be able to 
 */
	private static void testEncodeAndDecode() {
		Picture message = new Picture("msg.jpg");
		Picture image = new Picture("gorge.jpg");
		
		image.encodeAndDecode(message);

		image.explore();
	}

	private static void testChromakey() {
		// chroma key means to superimpose one image over another.  The image to be
		// drawn over the other one, only draws the pixels that aren't the chroma key
		// The common name for this is green screen
		Picture greenScreenImage = new Picture("maxresdefault.jpg");
		Picture background = new Picture("gorge1.jpg");
				
		greenScreenImage.applyGreenScreen(background);
		greenScreenImage.explore();
	}
	
	private static void testCopy() {
		// copies a picture onto another
		Picture beach = new Picture("beach.jpg");
		Picture caterpillar = new Picture("caterpillar.jpg");

		beach.copy(caterpillar, beach.getWidth() / 2, beach.getHeight() / 2);
		beach.explore();
		
	}

	private static void testMirrorDiagonal() {
		// Creates a new Picture that creates a mirror image along one diagonal

	}

	private static void testMirrorGull() {
		// creates a mirror image of a bird making image look like bird is over water

	}

	private static void testMirrorArms() {
		// TODO Auto-generated method stub

	}
	
	/** This method is an effort to make a Picture taken underwater look
	* more like it would be if the water were drained
	*/
	private static void testFixUnderwater() {
		Picture water = new Picture("water.jpg");
		water.fixUnderwater();
		water.explore();
	}

}