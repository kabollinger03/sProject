import java.util.Scanner;

public class InstructorFunction {

	public static void main(String[] args) {

		Scanner userIn = new Scanner(System.in); // Create a Scanner object

		int function = 1;
		while (function != 0) {
			System.out.println("What would you like to do?");
			System.out.println("Please Enter the number cooresponding to the operation you would like to complete");
			System.out.println("Enter 0 to exit.");
			System.out.println("1. Upload PDF");
			System.out.println("2. Email PDF");
			// String function = userIn.nextLine(); // Read user input
			function = userIn.nextInt();
			// System.out.println(function);

			switch (function) {
			case 0:
				break;
			case 1:
				System.out.println("UPLOAD PDF FUNCTIONALITY");
				break;
			case 2:
				System.out.println("EMAIL PDF FUNCTIONALITY");
				break;
			default:
				System.out.println("No such function, please choose again.");
			}
		}
		;
	}

}
