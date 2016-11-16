import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * This stores a bunch of WordCounts in an array.  It has the ability to add, remove, get and sort. The sort 
 * function takes in a parameter that descirbes 
 * 
 **/
public class WordStorage_HashMap {
	// instance variables - replace the example below with your own
	private int size  =  0;

	LinkedHashMap<String, Integer> wordCountMap = new LinkedHashMap<String, Integer>();

	@Override 
	public String toString() {
		String s  =  "WordStorage: ";

		String key = "";
		int value = 0;
		
		for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
			key = entry.getKey().toString();
			value = entry.getValue();
			
			s += "Word: " + key + " " + "Frequency: " + value + "\n";
		}
		return s;
	}

	public Object get(String key) {
		Integer value = 0;
		for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
			value = entry.getValue();			
		}
		return value;
	}

	public void add(String key) {
//		System.out.print("Key: " + key + " Value: " + wordCountMap.get(key) + 1 + "\n");
		wordCountMap.put(key, wordCountMap.get(key));
	}
	private void updateCount() {
		
	}

	public int size() {
		return size;
	}

	public WordCount[] getItems() {
		return (WordCount[]) wordCountMap.entrySet().toArray();
	} 

}
