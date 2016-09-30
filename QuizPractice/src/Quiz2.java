
public class Quiz2 {

	public static void main(String[] args) {
		
		final int numOccur = numOccur("hello", 'h');
		System.out.print("Num Occur: " + numOccur + "\n");
		
		final int numVowels = numVowels("Mississippi");
		System.out.print("Num Vowels: " + numVowels + "\n");
		
		final boolean consonantHeavy = isConsonantHeavy("Mississippi");
		System.out.print("isConsonantHeavy: " + consonantHeavy + "\n");
		
		final String pigLatinizedString = pigLatinize("dog");
		System.out.print("pigLatinizedString: " + pigLatinizedString + "\n");
		
		final double hexToDecimal = hexToDecimal("E7A9");
		System.out.print("hexToDecimal: " + hexToDecimal + "\n");
		
		final String intToHex = intToHex(61453);
		System.out.print("intToHex: " + intToHex + "\n");
	}
	
	//	Method determines number of times a character occurs in a string       
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
	
	//	Method to determine the number of vowels in a given string
	private static int numVowels(String s) {
		int numVowels = 0;

		s = s.toLowerCase();
		
		for (int i = 0; i < s.length(); i++) {
			if (numOccur(s.substring(i, i + 1), 'a') == 1 || 
				numOccur(s.substring(i, i + 1), 'e') == 1 || 
				numOccur(s.substring(i, i + 1), 'i') == 1 || 
				numOccur(s.substring(i, i + 1), 'o') == 1 || 
				numOccur(s.substring(i, i + 1), 'u') == 1) {
				numVowels++;
			}
		}
		return (numVowels);
	}
	
	private static boolean isConsonantHeavy(String s) {
		boolean isConsonantHeavy = false;
		final double numVowels = numVowels(s);
		final double numConsonants = s.length() - numVowels;
				
		if (numConsonants / numVowels >= 1.5) {
			isConsonantHeavy = true;
		}
		return (isConsonantHeavy);	
	}
	
	private static String pigLatinize(String s) {
		String pigLatinizedString = "";
		
		if (numVowels(s.substring(0,1)) != 1) {
			String firstChar = s.substring(0, 1);
			firstChar = firstChar.toLowerCase();

			s = s.substring(1);
			s = s + firstChar + "ay";
		} else {
			s = s + "way";
		}
		
		pigLatinizedString = s;
		
		return (pigLatinizedString);
	}
	
	private static double hexToDecimal(String num) {
		double decimalVal = 0;
		int currentPower = num.length() - 1;
		
		num = num.toLowerCase();
				
		for (int i = 0; i < num.length(); i++) {			
			switch (num.substring(i, i + 1)) {
				case "1":
					decimalVal += 1.0 * (Math.pow(16.0, currentPower));
					break;
				case "2":
					decimalVal += 2.0 * (Math.pow(16.0, currentPower));
					break;
				case "3":
					decimalVal += 3.0 * (Math.pow(16.0, currentPower));
					break;
				case "4":
					decimalVal += 4.0 * (Math.pow(16.0, currentPower));
					break;
				case "5":
					decimalVal += 5.0 * (Math.pow(16.0, currentPower));
					break;
				case "6":
					decimalVal += 6.0 * (Math.pow(16.0, currentPower));
					break;
				case "7":
					decimalVal += 7.0 * (Math.pow(16.0, currentPower));
					break;
				case "8":
					decimalVal += 8.0 * (Math.pow(16.0, currentPower));
					break;
				case "9":
					decimalVal += 9.0 * (Math.pow(16.0, currentPower));
					break;
				case "a":
					decimalVal += 10.0 * (Math.pow(16.0, currentPower));
					break;
				case "b":
					decimalVal += 11.0 * (Math.pow(16.0, currentPower));
					break;
				case "c":
					decimalVal += 12.0 * (Math.pow(16.0, currentPower));
					break;
				case "d":
					decimalVal += 13.0 * (Math.pow(16.0, currentPower));
					break;
				case "e":
					decimalVal += 14.0 * (Math.pow(16.0, currentPower));
					break;
				case "f":
					decimalVal += 15.0 * (Math.pow(16.0, currentPower));
					break;
			}
			currentPower--;
		}	
		return (decimalVal);
	}
	
	private static String intToHex(int hex) {
		String hexString = "";
		final int base = 16;	//	Specify the base value
		final char[] hexDigits = {	//	An array of chars to help us compose a hex string from our input int value
				 '0', '1', '2', '3', 
				 '4', '5', '6', '7', 
				 '8', '9', 'A', 'B', 
				 'C', 'D', 'E', 'F'
				 };
		
		//	Return an empty string if 'hex' is empty
		if (hex <= 0) { return hexString; };
		
		while (hex != 0) {
			int digit = hex % base;	//	Determine hex value by dividing the input 'hex' value by the base value and getting the remainder
			hexString = hexDigits[digit] + hexString;	//	Compose part of the hex string
			hex /= base;	//	Decrement the 'hex' value
		}
		return hexString;
	}	
}



