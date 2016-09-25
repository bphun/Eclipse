import java.util.ArrayList;

public class Quiz2 {

	public static void main(String[] args) {
		
		int numOccur = numOccur("hello", 'h');
		System.out.print("Num Occur: " + numOccur + "\n");
		
		int numVowels = numVowels("Hello");
		System.out.print("Num Vowels: " + numVowels + "\n");
		
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
		ArrayList<Character> vowelList = new ArrayList<Character>();
		
		vowelList.add('a');
		vowelList.add('e');
		vowelList.add('i');
		vowelList.add('o');
		vowelList.add('u');

		s = s.toLowerCase();
		
		for (int character = 0; character < s.length(); character++) {
			for (int vowel = 0; vowel < vowelList.size(); vowel++) {
				if (s.charAt(character) == vowelList.get(vowel)) {
					numVowels++;
				}
			}
		}
		return (numVowels);
	}
	
	
}
