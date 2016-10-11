import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * Convenience class for getting input from the mymedia.txt file
 * 
 * @author ckinnard
 * @version 3/5/16
 */
public class MediaFile {
    private static final String DATA_FILE = "mymedia.txt";

    private static Scanner in;
    private static BufferedWriter out;

    /**
     * Opens a file to be used for input (if not already open),
     * reads a line from the file, and returns the entire line of data.
     * 
     * @return a line of text from the input file
     */
    public static String readString() {
        if (in == null) {
            try {
                in = new Scanner(new File(DATA_FILE));
            }
            catch (Exception e) {
                System.err.println("Cannot open file for input!");
                e.printStackTrace();
            }
        }
        try {
            if (in.hasNext()) {
            	String s = in.nextLine();
                return s;	
            }
        	
//            while (in.hasNext()) {
//            	if (in.next() != "  ") {
//                    String s = in.nextLine();
//                    return s;	
//            	} 
//            }
        }
        catch (Exception e) {
            System.err.println("Cannot read  file!");
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Oens a file to be used for output (if not already open),
     * writes a string to the file and writes a newline.
     * 
     * @param s The string text to be written. Following the string, a newline is added to the file.
     */

    public void writeString(String s) {
        if (out == null) {
            try {
                out = new BufferedWriter(new FileWriter(DATA_FILE));
            }
            catch (Exception e) {
                System.err.println("Cannot create file for output!");
                e.printStackTrace();
            }
        }

        try {
            out.write(s);
//          out.write("|");
//            out.write("\n");
          out.write("|");

        }
        catch (Exception e) {
            System.err.println("Cannot write file!");
            e.printStackTrace();
        }

    }

    /**
     * Saves and closes the file (when opened for either input or output). 
     * 
     * Note: If the program terminates before the file is closed,
     * no data will be saved or written to the file.
     */
    public static void saveAndClose() {
        if (in != null) {
            try {
                in.close();
                in = null;
            }
            catch (Exception e) {
                System.err.println("Cannot close input file!");
                e.printStackTrace();
            }
        }      
        if (out != null) {
            try {
                out.close();
                out = null;
            }
            catch (Exception e) {
                System.err.println("Cannot close output file!");
                e.printStackTrace();
            }
        }
    }
    
    
	/**
	 * Use each Song's toString method to create a String that represents the Song, then, write that String
	 * to the file opened by this method.  It would be a good idea to use the writeString method from above, 
	 * or you can use the logic that is found there in this method.
	 * 
	 * @param list The List of Song Objects to be written to the file
	 * @param fileName the name of the file to be opened (or created if non-existent) and subsequently written to
	 * 
	 * @return true if successfully written, false otherwise
	 */
	public static void writeSongListToFile(List<Song> list, String fileName){

		fileName += ".txt";

		if (out == null) {
			try {
				out = new BufferedWriter(new FileWriter(fileName));
			} catch (Exception e) {
				System.err.print("Error creating file " + fileName);
				e.printStackTrace();
			}
		}

		try {
			for (final Song s : list) {
				out.write(s.toString() + "\n");
			}

		} catch (Exception e) {
			System.err.print("Error writing songs to file " + fileName);
			e.printStackTrace();
		}
		saveAndClose();
	}

	/**
	 * Give me all the info about the songs in the specified fileName
	 * 
	 * @param fileName name of the file to be opened and processed.
	 * 
	 * @return The list of Strings that represent the songs found in the file
	 */ 
	public static ArrayList<String> readInSongInfo(String fileName){
		// assume the Scanner is not currently assigned to a file
		ArrayList<String> songz = new ArrayList<String>();
		try{
			in = new Scanner(new File(fileName));
		}
		catch(Exception lior){
			System.out.println("Trouble!! "+lior);
		}

		while(true) {
			String songString = readString();

			if (songString == null){
				break;
			} else{
				songz.add(songString);
			}
		}
		return songz;
	}
    
}
