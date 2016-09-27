
public class Quiz2 {

	public static void main(String[] args) {
		
		int numOccur = numOccur("hello", 'h');
		System.out.print("Num Occur: " + numOccur + "\n");
		
		int numVowels = numVowels("Mississippi");
		System.out.print("Num Vowels: " + numVowels + "\n");
		
		boolean consonantHeavy = isConsonantHeavy("Mississippi");
		System.out.print("isConsonantHeavy: " + consonantHeavy + "\n");
		
	}
	
	private static int numOccur(String s, char let) {
		int numOccur = 0;

		s = s.toLowerCase();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == let) {
				numOccur++;
			}
		}
		return (numOccur);
	}
	
	private static int numVowels(String s) {
		int numVowels = 0;

		s = s.toLowerCase();
		
		for (int i = 0; i < s.length(); i++) {
			if (numOccur(s.substring(i, i + 1), 'a') == 1 || numOccur(s.substring(i, i + 1), 'e') == 1|| numOccur(s.substring(i, i + 1), 'i') == 1|| numOccur(s.substring(i, i + 1), 'o') == 1|| numOccur(s.substring(i, i + 1), 'u') == 1) {
				numVowels++;
			}
		}
		
		return (numVowels);
	}
	
	private static boolean isConsonantHeavy(String s) {
		boolean isConsonantHeavy = false;
		double numVowels = numVowels(s);
		double numConsonants = s.length() - numVowels;
		
		if (numConsonants / numVowels >= 1.5) {
			isConsonantHeavy = true;
		}
		return isConsonantHeavy;	
	}
	
}
