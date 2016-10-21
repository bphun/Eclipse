
/**
 * Write a description of class ProcessNovel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProcessNovel
{
    WordStorage ws = new WordStorage();

    

    public static void main(String[] args){
        // prompt the user for the text file to be processed
        promptForFile();
        // read in the contents of the text file, adding to the WordStorage as they are input
        readInNovel();
        // print out the results    
        displayResults();
        
        WordStorage ws = new WordStorage();
        System.out.println(ws);
        
        ws.add("asp");
        ws.add("ask");
        ws.add("at");
        ws.add("ask");
        ws.add("at");
        System.out.println(ws); 
    }
    public static void promptForFile(){
       // JFileChooser
    }
    
    public static void readInNovel(){
        
    }
    
    public static void displayResults(){
        
    }
    
}
