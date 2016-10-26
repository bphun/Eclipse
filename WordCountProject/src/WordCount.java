
/**
 * WordCount class will encapsulate a String and frequency.  It is mainly a storage device;
 * It DOES need the ability to sort according to three attributes:  Alphabetically, frequency, length
 * 
 */
public class WordCount implements Comparable<WordCount> {
    private String word = "";
    private int count = 0;

    public WordCount(String w) {
        word = w;
        this.count = 1; 
    }

    public void addCount() {
        count++;
    }

    public String getWord() { return word; }

    public int getFrequency() { return count; }
    
    @Override
    public int compareTo(WordCount wc) {
        return this.word.compareTo(wc.getWord());
    }

    @Override
    public boolean equals(Object wc) {
        try {
            return compareTo((WordCount)wc) == 0;
        } catch (Exception e) {
            return false;
        }

    }
    @Override
    public String toString() {
        return "Word: " + word + ", count: " + count;
    }
}
