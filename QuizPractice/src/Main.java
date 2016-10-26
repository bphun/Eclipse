
public class Main {

	static String[] stringArray = {"wheels", "on", "the", "bus"};
	
	
	public static void main(String[] args) {
		RandomStringChooser_APCS_2016 sChooser = new RandomStringChooser_APCS_2016(stringArray);
		for (int i = 0; i < 6; i++) {
			System.out.println(sChooser.getNext());
		}
	}
}
