import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Collection;
import java.io.BufferedWriter;

public class HW_Assignment1 {

	private ArrayList<Double> numbers;

	private double min, max, mean, standardDeviation;

	public static void main(String[] args) {
		new HW_Assignment1().start();
	}

	private void start() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Type the .txt file or doubles that you want to be processed");

		String firstInput = scanner.nextLine();
		if (firstInput.contains(".txt")) {
			System.out.println("Reading in text file");
			readTextFile(firstInput);
		} else {
			numbers = new ArrayList<>();
			numbers.add(Double.parseDouble(firstInput));
			readUserInput(scanner);
		}

		System.out.println("Processing Numbers");
		processNumbers();


		System.out.println("Min: " + min + " Max: " + max + " Mean: " + mean + " Standard Deviation: " + standardDeviation);
		
		System.out.println("Would you like to write this data to a text file?[y/n]");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			BufferedWriter bufferedWriter = null;
			try {
				bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
				bufferedWriter.write("Min: " + min + "\nMax: " + max + "\nMean: " + mean + "\nStandard Deviation: " + standardDeviation);
				bufferedWriter.close();
			} catch (IOException e) {
				System.err.println("Error writing output to output.txt");
				e.printStackTrace();
			}
		}
		scanner.close();

	}

	private void readTextFile(String fileName) {
		numbers = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(fileName));
			
			String line = bufferedReader.readLine();
			while (line != null && !line.equals("") ) {
				numbers.add(Double.parseDouble(line));
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("Error reading text file");
			e.printStackTrace();
		}
	}

	private void readUserInput(Scanner scanner) {
		
		String line = scanner.nextLine();

		while (!line.contains(" ") && !line.equals("")) { /*!line.matches("^\\s*$")*/
			numbers.add(Double.parseDouble(line));
			line = scanner.nextLine();
		}
	}
	
	private void processNumbers() {
		min = findMin();
		max = findMax();
		mean = calcMean();
		standardDeviation = calcStandardDeviation();
	}

	private double findMin() {
		double min = numbers.get(0);
		for (double d : numbers) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}

	private double findMax() {
		double max = numbers.get(0);
		for (double d : numbers) {
			if (d > max) {
				max = d;
			}
		}
		return max;
	}

	private double calcMean() {
		double total = 0;
		for (double d : numbers) {
			total += d;
		}
		return total / (numbers.size());
	}

	private double calcStandardDeviation() {
		double mean = calcMean();
		
		double difs = 0;
		for (int i = 0; i < numbers.size(); i++) {
			difs += Math.pow(numbers.get(i) - mean, 2);
		}

		return Math.sqrt(difs / (numbers.size() - 1));
	}
}
