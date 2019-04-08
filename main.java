import java.util.Scanner;

public class Main {

    /*
     * @param args the command line arguments
     */
	public static void main(String[] args){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR - cannot connect to database. Cannot find database driver.");
			System.exit(-1);
		}
		
		Scanner sc= new Scanner(System.in);
		Login login = new Login();
		login.getUserLoginInfo(sc);

		if(login.isAdmin()){
			Login.getAdminPage(sc);
		}
		else{
			Login.getInstructorPage(sc);
		}
	}
    
}
