
import java.sql.*;


public class InsertFunctions {
/*Java class to access and insert new data into the DataBase
 * for the modules, categories, courses, and streams tables.
 */
	
	
	
	
	
	
		//Connect to DB
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		//c1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
		
		
		//INSERT FOR STREAM
		public static void insertStream(String id, String name){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection c1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
		Statement state = c1.createStatement();
		ResultSet r1 =state.executeQuery("INSERT INTO stream VALUES('"+id+"', '"+name+"')");
		System.out.println("Insert Statement Executed"); //Take this line out if you don't want a confirmation message
		c1.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		//INSERT FOR CLASS
		
		public static void insertClass(String id, String name, String idFK){
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
		Statement state2 = c1.createStatement();
		ResultSet r1=state2.executeQuery("INSERT INTO class VALUES('"+id+"', '"+name+"', '"+idFK+"')");
		System.out.println("Insert Statement Executed"); //Take this line out if you don't want a confirmation message
		c1.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		}
		//INSERT FOR MODULES
		public static void insertModules(int id, String name, String category, String streamID){
		try{Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
		Statement state = c1.createStatement();
		ResultSet r1=state.executeQuery("INSERT INTO modules VALUES("+id+", '"+name+"', '"+category+"', '"+streamID+"')");
		System.out.println("Insert Statement Executed"); //Take this line out if you don't want a confirmation message
		c1.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		}
		//INSERT FOR COURSES
		public static void insertCourses(String courseID, String name, int moduleID){
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
		Statement state=c1.createStatement();
		ResultSet r1=state.executeQuery("INSERT INTO courses VALUES('"+courseID+"', '"+name+"', "+moduleID+")");
		System.out.println("Insert Statement Executed"); //Take this line out if you don't want a confirmation message
		c1.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		}
		
}
	
	
	
	

