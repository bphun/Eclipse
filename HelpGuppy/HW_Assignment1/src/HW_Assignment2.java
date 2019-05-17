import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class HW_Assignment2 {

	HashSet<String> novelWords;

	public static void main(String[] args) {
		new HW_Assignment2().start();
	}

	private void start() {
		readInFile();
	}

	private void readInFile() {
		novelWords = new HashSet<>();

		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new FileReader("novel.txt"));

			String line = bufferedReader.readLine();
			while (line != null) {
				String[] words = line.split("[^a-zA-Z0-9]");

				for (String s : words) {
					novelWords.add(s);
				}
				line = bufferedReader.readLine();
			}

		} catch (IOException e) {
			System.err.println("Error reading text file");
			e.printStackTrace();
		}
	}
}