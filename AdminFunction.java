package AdminPages;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class AdminFunction  {
	public static Scanner userIn = new Scanner(System.in);
	
	
	public static void main(String [] args)
	{
		getAdminPage(userIn);
	}
	public static void getAdminPage(Scanner userIn) 
	{
		
		int function = 1;
		while (function != 0) {
			System.out.println("Welcome Admin");
			System.out.println("Please Enter the number cooresponding to the operation you would like to complete: ");
			System.out.println("Enter 0 to Logout.");
			System.out.println("1. Manage modules");
			System.out.println("2. Manage users");
			System.out.println("3. Manage courses");
			System.out.println("4. Manage streams");
			System.out.println("5. Instructor Page");
			
			function = userIn.nextInt();
			
			
		

			switch (function) {
			case 0:
				getUserLoginInfo(userIn);
				break;
			case 1:
				System.out.println(" Enter to MAKE CHANGES TO MODULES FUNCTIONALITY");
				System.out.println("0. To go back");
				System.out.println("1. Create a module");
				System.out.println("2. Delete a module");
				System.out.println("3. Update a module");
				int function2 = userIn.nextInt();
				switch (function2) {
				case 0:
					break;
				case 1:
					try {
			            Class.forName("oracle.jdbc.driver.OracleDriver"); 
			            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
			            Statement st = con.createStatement();
			            
			            AdminFunctionality.createModule(st);
			            
			            con.commit();
			            st.close();
			           con.close();
			        } catch (Exception ex) {
			             System.out.println(ex);
			             System.out.println("not connected");
			        }	
					break;
				case 2:
					try {
			            Class.forName("oracle.jdbc.driver.OracleDriver"); 
			            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
			            Statement st = con.createStatement();
			            
			            AdminFunctionality.deleteModule(st);
			            
			            con.commit();
			            st.close();
			           con.close();
			        } catch (Exception ex) {
			             System.out.println(ex);
			             System.out.println("not connected");
			        }	
					break;
				case 3:
					try {
			            Class.forName("oracle.jdbc.driver.OracleDriver"); 
			            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
			            Statement st = con.createStatement();
			            
			            AdminFunctionality.updateModule(st);
			            
			            con.commit();
			            st.close();
			           con.close();
			        } catch (Exception ex) {
			             System.out.println(ex);
			             System.out.println("not connected");
			        }	
					break;
				default: System.out.println("No such function, please choose again.");
				break;

				}
				case 2:
					try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); 
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
		            Statement st = con.createStatement();
		            
		            manageUsers manageUser = new manageUsers();
		            manageUser.userOptionsForUsers(st);
		            
		            con.commit();
		            st.close();
		            con.close();
				}catch (Exception ex) {
			     System.out.println(ex);
			     System.out.println("not connected");
			    }	
				break;
			case 3:
				try {
		            Class.forName("oracle.jdbc.driver.OracleDriver"); 
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
		            Statement st = con.createStatement();
		            
		            manageCourses manageCourses = new manageCourses();
		            manageCourses.userOptionsForCourses(st);
		            
		            con.commit();
		            st.close();
		            con.close();
		        } catch (Exception ex) {
		             System.out.println(ex);
		             System.out.println("not connected");
		        }	
				break;
			case 4:
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver"); 
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
		            Statement st = con.createStatement();
		            
		            manageStream manageStreams = new manageStream();
		            manageStreams.userOptionsForStream(st);
		            
		            con.commit();
		            st.close();
		            con.close();
				}catch (Exception ex) {
			     System.out.println(ex);
			     System.out.println("not connected");
			    }	
				break;
				
			case 5:
				getInstructorPage(userIn);
				break;
			default:
				System.out.println("No such function, please choose again.");
			}
		}
		;
	}
	

}