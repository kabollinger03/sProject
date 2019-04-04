


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class usersTest {

		public static void main(String[] args) {
			 try {
		            Class.forName("oracle.jdbc.driver.OracleDriver"); 
		            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
		            Statement st = con.createStatement();

		            //userCRUD.insertUser(st, "Phillip@syntelinc.com", "password", "0");
		            userCRUD.deleteUser(st, "Phillip@syntelinc.com");
		            
		            
//		            con.commit();
		            st.close();
		           con.close();
		        } catch (Exception ex) {
		             System.out.println(ex);
		             System.out.println("not connected");
		        }	
		}
}
