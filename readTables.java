
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OracleTypes;



public class readTables {
    public static void main(String[] args) {
        String module_id = "5";
        String course_id = "OR1.3";
        String stream_id = "FSD123";
        
        readTables rt = new readTables();
        
        try {
            //Step1 - Register Driver
            Class.forName("oracle.jdbc.driver.OracleDriver"); // Type 4 Driver Pure Java Driver

            //Step2 - Create Connection
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");

            //Step3 - Create Statement
            Statement st = con.createStatement();
   
            //Step 4 - Generate Resultset
            rt.readCourses(st, course_id);
            rt.readModules(st, module_id);
            rt.readStreams(st, stream_id);
                        
            st.close();
            con.close();
            
        } catch (Exception ex) {
             System.out.println(ex);
        }
    }
    
    
    public void readCourses(Statement st, String course_id){
		try {
			System.out.println("Reading class...");
			ResultSet rs = st.executeQuery("select course_id, course_name, module_id from Student_Performance.Courses WHERE course_id='" + course_id + "'");
			while(rs.next())
				System.out.println("Result: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
	}
    
    public void readStreams(Statement st, String stream_id){
		try {
			System.out.println("Reading class...");
			ResultSet rs = st.executeQuery("select stream_id, stream_name from Student_Performance.Stream WHERE stream_id='" + stream_id + "'");
			while(rs.next())
				System.out.println("Result: " + rs.getString(1) + " " + rs.getString(2));
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
	}
    
    public void readModules(Statement st, String module_id){
		try {
			System.out.println("Reading class...");
			ResultSet rs = st.executeQuery("select module_id, module_name, category, stream_id from Student_Performance.Modules WHERE module_id='" + module_id + "'");
			while(rs.next())
				System.out.println("Result: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
	}
    
}
