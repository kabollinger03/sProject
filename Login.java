import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner; 


public class Login {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	/* initiate the login process
	 * @param comon scanner
	 */
	public void getUserLoginInfo(Scanner sc){

		//Loop until user enters a correct user name
		
		do {
			System.out.println("Enter Username: "); 
	        username = sc.nextLine();
	    } while(!verifyEmail(username));
		
		
		//Loop until user enters correct password		
		do {
			System.out.println("Enter Password: "); 
	        password = sc.nextLine();
	    } while(!validateInDb(username, password));

	}
	
	public boolean isAdmin(){
		try( Connection con = DriverManager.getConnection(Main.dbString, Main.dbUser, Main.dbPw);
			 Statement st = con.createStatement();
		){
			ResultSet rs = st.executeQuery("select isadmin from Users where user_id = " + "'" + username +  "'");
			String flag = null;
			while(rs.next())
			flag = rs.getString("isAdmin");

			return flag.equals("Y");                
	    } catch (Exception ex) 	{
			System.out.println("Database Error: " + ex.getMessage());
			return false;
	    }
	}
	
	public boolean verifyEmail(String email_address){
		String syntel_domain = "@syntelinc.com";
		
		if(email_address.toLowerCase().contains(syntel_domain.toLowerCase())){
			return true;
		}
			
		else{
			System.out.println("Incorrect Email Domain");
			return false;
		}
			
	}
	
	static boolean validateInDb(String username, String password){
		int count = 0;
		try( Connection con = DriverManager.getConnection(Main.dbString, Main.dbUser, Main.dbPw);
			 Statement st = con.createStatement();
		){
			ResultSet rs = st.executeQuery("select user_id, password from Users where user_id = " + "'" + username + "'" + "and password =" + "'" + password + "'");
								
			while(rs.next()) {++count; }                
	    } catch (Exception ex) 	{
			System.out.println("Database Error: " + ex.getMessage());
			return false;
	    }
		
		return (count == 1);
	}

	
	public static void getAdminPage(Scanner userIn) {

            AdminFunction.getAdminPage(userIn);
	}

	public static void getInstructorPage(Scanner userIn) {
		//Scanner userIn = new Scanner(System.in); // Create a Scanner object

		int function = 1;
		while (function != 0) {
			System.out.println("What would you like to do?");
			System.out.println("Please Enter the number cooresponding to the operation you would like to complete");
			System.out.println("Enter 0 to Log Out.");
			System.out.println("1. Create Class");			
			System.out.println("2. Download Excel Template");
			//System.out.println("3. Admin Tasks");
			// String function = userIn.nextLine(); // Read user input
			function = userIn.nextInt();
			// System.out.println(function);

			switch (function) {
			case 0:
				System.out.println("Logging Out");
				break;
			case 1:
				System.out.println("CREATING CLASS FUNCTIONALITY");
                        {
                            try {
                                System.out.println("Enter your excel file path here");
                                String file = userIn.next();
                                ArrayList<Employee> employees = Runner.ExcelUpload(file);
                                
                                SendEmail.prompt(userIn);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                          
                        }
				break;
			case 2:
				System.out.println("DOWNLOADING TEMPLATE FUNCTIONALITY");
                                DownloadTemplateMenu.createTemplate(userIn);
				break;
			default:
				System.out.println("No such function, please choose again.");
			}
		}
		;
		//userIn.close();

	}
}