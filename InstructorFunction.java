import java.util.Scanner;

public class InstructorFunction {

	public static void main(String[] args) {

		Scanner userIn = new Scanner(System.in); // Create a Scanner object

		int function = 1;
		while (function != 0) {
			System.out.println("What would you like to do?");
			System.out.println("Please Enter the number cooresponding to the operation you would like to complete");
			System.out.println("Enter 0 to Log Out.");
			System.out.println("1. Create Class");
			System.out.println("2. Download PDF");
			// String function = userIn.nextLine(); // Read user input
			function = userIn.nextInt();
			// System.out.println(function);

			switch (function) {
			case 0:
				userIn.close();
				System.out.println("Logging Out");
				break;
			case 1:
				System.out.println("CREATE CLASS FUNCTIONALITY");
				break;
			case 2:
				System.out.println("DOWNLOAD PDF FUNCTIONALITY");
				break;

			default:
				System.out.println("No such function, please choose again.");
			}
		}
		;
	}

}
