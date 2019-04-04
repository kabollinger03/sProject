import java.util.Scanner;

public class AdminFunction {
	public static void main(String[] args) {

		Scanner userIn = new Scanner(System.in); // Create a Scanner object

		int function = 1;
		while (function != 0) {
			System.out.println("What would you like to do?");
			System.out.println("Please Enter the number cooresponding to the operation you would like to complete");
			System.out.println("Enter 0 to exit.");
			System.out.println("1. Make changes to modules");
			System.out.println("2. Manage categories");
			System.out.println("3. Manage other admins");
			System.out.println("4. Manage instructors");
			System.out.println("5. Manage courses");
			// String function = userIn.nextLine(); // Read user input
			function = userIn.nextInt();
			// System.out.println(function);

			switch (function) {
			case 0:
				break;
			case 1:
				System.out.println("MAKE CHANGES TO MODULES FUNCTIONALITY");
				break;
			case 2:
				System.out.println("MANAGE CATEGORIES FUNCTIONALITY");
				break;
			case 3:
				System.out.println("MANAGE ADMINS FUNCTIONALITY");
				break;
			case 4:
				System.out.println("MANAGE INSTRUCTORS FUNCTIONALITY");
				break;
			case 5:
				System.out.println("MANAGE COURSES5 FUNCTIONALITY");
				break;
				
			default:
				System.out.println("No such function, please choose again.");
			}
		}
		;
	}
	

}
