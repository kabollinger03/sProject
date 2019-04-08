
import AdminPages.AdminFunction;
import extraction.sProject.Employee;
import extraction.sProject.Runner;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner; 
import java.util.logging.Level;
import java.util.logging.Logger;
import sProject.DownloadTemplateMenu;

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
	
	//Call getUserLoginInfo to initiate the login process
	public void getUserLoginInfo(Scanner sc){
		//Validate validate = new Validate();
		//Scanner sc = new Scanner(System.in); 
		String username;
		String password; 
		
		//Loop until user enters a correct user name
		
		do{
			System.out.println("Enter Username: "); 
	        username = sc.nextLine();
	        if(verifyEmail(username) == true){
	        	setUsername(username);
	        	break;
	        }
	    }while(true);
		
		
		//Loop until user enters correct password
		
		do{
			System.out.println("Enter Password: "); 
	        password = sc.nextLine();
	        setPassword(password);
	        
	        //Will return "TRUE" if the un/pw exists
	        if(validateInDb(this.username, this.password)){
	        	System.out.println("Login Correct: Proceeding");
	        	//sc.close();
	        	break;
	        }
	        
	        else{
	        	System.out.println("Login information was incorrect");
	        }
	    }while(true);
		
		//sc.close();
	}
	
	public boolean isAdmin(){
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Type 4 Driver Pure Java Driver
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select isadmin from Users where user_id = " + "'" + this.username +  "'");
			String flag = null;
			while(rs.next())
			flag = rs.getString("isAdmin");
			
			
			con.commit();
	        st.close();
	        con.close();
			if (flag.equals("Y")) {
				System.out.println("Admin Detected");
				return true;

			}
			else
			{
				System.out.println("Instructor Detected");
				return false;
			}
                
	    }
		
		catch (Exception ex) 
		{
			System.out.println(ex);
	    }
		return false;
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
		//Number 2
		int count = 0;
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver"); // Type 4 Driver Pure Java Driver
					Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select user_id, password from Users where user_id = " + "'" + username + "'" + "and password =" + "'" + password + "'");
					
										
					while(rs.next())
					{
						++count;
					}
					
					con.commit();
			        st.close();
			        con.close();
		                
			    }
				
				catch (Exception ex) 
				{
					System.out.println(ex);
			    }
				
				if(count == 0 || count > 1)
				{
					return false;
				}
				else
				{
					return true;
				}
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
	
	public static void main(String[] args){
		Scanner sc= new Scanner(System.in).useDelimiter("\\n");
		Login login = new Login();
		login.getUserLoginInfo(sc);
		//sc.nextInt();
		if(login.isAdmin()){ //admin page
			getAdminPage(sc);
		}
		else{ //instructor page
			getInstructorPage(sc);
			
		}
	}
}