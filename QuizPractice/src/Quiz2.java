
public class Quiz2 {

	public static void main(String[] args) {
		numOccur("hello", 'h');
	}
	
	public static int numOccur(String s, char let) {
		
		s = s.toLowerCase();
		
		int numOccur = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == let) {
				numOccur++;
			}
		}
		
		System.out.print("Num occur: " + numOccur);
		return (numOccur);
	}
	
}
