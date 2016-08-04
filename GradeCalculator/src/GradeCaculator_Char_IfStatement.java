
public class GradeCaculator_Char_IfStatement {

	public static void main(String[] args) {

		double gradePercent = 86.7;
		char grade = 'A';
		
		if (gradePercent >= 90) {
			grade = 'A';
			System.out.print("Grade" + grade);
			
		} else if (gradePercent >= 80) {
			grade = 'B';
			System.out.print("Grade" + grade);

		} else if (gradePercent >= 70) {
			grade = 'C';
			System.out.print("Grade" + grade);

		} else if (gradePercent >= 60) {
			grade = 'D';
			System.out.print("Grade" + grade);

		} else if (gradePercent <= 50) {
			grade = 'F';
			System.out.print("Grade" + grade);
		}
	}
	
}
