
public class HiddenWord_APCS2015 {

	static String hiddenWord = "HARPS";
	
	public HiddenWord_APCS2015(String w) {
		this.hiddenWord = w;
	}
	
	public static void main(String[] args) {
		System.out.print(getHint("AAAAA"));
	}
	
	public static String getHint(String s) {
		
		if (s.length() <= 1) { return s; }
		
		String hint = "";
		String[] guessCharArray = s.split("");
		
		for (int i = 0; i < guessCharArray.length; i++) {
			if (hiddenWord.contains(guessCharArray[i])) {
				if (hiddenWord.indexOf(guessCharArray[i]) == i) {
					hint += guessCharArray[i];
				} else {
					hint += "+";
				}
			} else {
				hint += "*";
			}
		}
		return hint;
	}
	
	
}
