package com.brandonphan.medialib.Media;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Convenience class for getting input from the mymedia.txt file
 * 
 * @author ckinnard
 * @version 3/5/16
 */
public class MediaFile {
    private static final String DATA_FILE = "mymedia.txt";

//    private static Scanner in;
    private static Scanner in;
    private static BufferedWriter out;
    private static Context context;
//    static MainActivity mainActivity = new MainActivity();
//
//    static Context context = mainActivity.getApplicationContext();

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
                System.err.println("Cannot open file " + DATA_FILE);
                e.printStackTrace();
            }
        }
        try {
            if (in.hasNext()) {
                String s = in.nextLine();
                return s;
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            System.err.println("Cannot read file " + DATA_FILE);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Oens a file to be used for output (if not already open),
     * writes a string to the file and wrties a newline.
     *
     * @param s The string text to be written. Follwing the string, a newline is added to the file.
     */

    public static void writeString(String s) {
        if (out == null) {
            try {
                out = new BufferedWriter(new FileWriter(DATA_FILE));
            } catch (Exception e) {
                System.err.println("Cannot create file for output!" + DATA_FILE);
                e.printStackTrace();
                return;
            }
        }

        try {
            out.write(s);
            // out.newLine();
            out.write("|");
        } catch (Exception e) {
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
            } catch (Exception e) {
                System.err.println("Cannot close input file! " + DATA_FILE);
                e.printStackTrace();
                return;
            }
        }
        if (out != null) {
            try {
                out.close();
                out = null;
            } catch (Exception e) {
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
                return;
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

    static AssetManager assetManager;
    static InputStream in1;
    public static ArrayList<String> readInMediaInfo(String fileName) {
        // assume the Scanner is not currently assigned to a file
        ArrayList<String> songs = new ArrayList<String>();
        try {

            in1 = new Resources(assetManager, null, null).getAssets().open(fileName);

            in = new Scanner(new File(fileName));
        } catch(Exception e){
            System.out.println("Error: " + e);
            e.printStackTrace();
            return null;
        }

        while (true) {
            String mediaString = readString();
            if (mediaString != null){
                songs.add(mediaString);
            } else {
                break;
            }
        }
        return songs;
    }
}
