
public class Continue_Loops {

	public static void main(String[] args) {
		
		String searchString = "Brandon Bhan".toLowerCase(); /* Convert string to lower case */
		char searchLetter = 'b';
		int max = searchString.length(); /* Get length of string */
		int numberOfSearchChar = 0;
		
		// For loop declaration
		for (int i = 0; i < max; i++) {
			searchLetter = Character.toLowerCase(searchLetter);
			if (searchString.charAt(i) != searchLetter) {
				continue;  /*Continue because we didn't find the specified character */
			} else {
				numberOfSearchChar++;
			}
		}
		if (numberOfSearchChar <= 1) {
			System.out.print("Found " + numberOfSearchChar + " " + searchLetter + " in the string");			
		} else {
			System.out.print("Found " + numberOfSearchChar + " " + Character.toUpperCase(searchLetter) + "'s in the string");			
		}
	} 
	
}
