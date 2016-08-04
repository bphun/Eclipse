
public class IntSearch_Arrays_ForLoops {

	public static void main(String[] args) {
		int i;
		int[] numbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40}; /* Declare an array */		
		
		int searchNumber = 12;
		boolean foundNumber = false;
		
		/* For loop declaration */
		for (i = 0; i < numbers.length; i++) {
            if (numbers[i] == searchNumber) {  /* Check if the current element == the number we are looking for */
            	foundNumber = true;
            	break; /* Break out of the loop because we found the number we are looking for */
            }
		}
		
		if (foundNumber) {
			System.out.println("Found " + searchNumber + " at index " + i);
		} else {
			System.out.println(searchNumber + " not in the array");
		}
	}
	
	
}
