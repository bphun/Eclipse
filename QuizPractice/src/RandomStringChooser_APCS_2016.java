import java.util.ArrayList;
import java.util.Random;

public class RandomStringChooser_APCS_2016 {

	ArrayList<String> stringArray = new ArrayList<>();
	
	public RandomStringChooser_APCS_2016(String[] sArr) {
		for (final String s: stringArray) {
			stringArray.add(s);
			System.out.println(s);
		}
	}
	
	public String getNext() {
		if (stringArray.size() <= 0) { return "NONE"; }
		
		Random random = new Random();
		int randIndex = (int) (random.nextInt()* stringArray.size());
		String randString = stringArray.get(randIndex);
		stringArray.remove(randIndex);
		return randString;
	}
	
}
