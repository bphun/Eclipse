
/**
 * WordCount class will encapsulate a String and frequency.  It is mainly a storage device;
 * It DOES need the ability to sort according to three attributes:  Alphabetically, frequency, length
 * 
 */
public class WordCount implements Comparable<WordCount>
{
    private String word;
    private int freq;
    
    public WordCount(String s){
        word = s;
        this.freq = 1;
    }
    
    public int addFreq(){
        freq++;
        return freq;
    }
    
    public String getWord(){
        return word;
    }
    public int getFreq(){
        return freq;
    }
   @Override
    public int compareTo(WordCount wc){
       //return this.getWord().compareTo(wc.getWord());
       //return this.freq - wc.freq;
       return word.length()-wc.getWord().length();
       //return 0; 
    }
    
    @Override
    public boolean equals(Object wc){
        // return 
    }
    
    @Override
    public String toString(){
        return "word: "+word+" freq: "+freq;
    }
    
}
