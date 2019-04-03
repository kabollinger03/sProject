
import java.sql.*;


public class InsertFunctions {
/*Java class to access and insert new data into the DataBase
 * for the modules, categories, courses, and streams tables.
 */
	
	
	 
	public static void main(String[] args){
		try {
			Statement state;
			Connection c1;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			c1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Student_Performance", "Student_Performance");
			state = c1.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
		
		
		//INSERT FOR STREAM
		public void insertStream(Statement state, String id, String name){
		try {
		int r1 =state.executeUpdate("INSERT INTO stream VALUES('"+id+"', '"+name+"')");
		System.out.println(r1+" rows inserted."); //Take this line out if you don't want a confirmation message
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		//INSERT FOR CLASS
		
		public static void insertClass(Statement state, String id, String name, String idFK){
		try{
		int r1=state.executeUpdate("INSERT INTO class VALUES('"+id+"', '"+name+"', '"+idFK+"')");
		System.out.println(r1+" rows inserted."); //Take this line out if you don't want a confirmation message
		
		} catch(Exception e){
			e.printStackTrace();
		}
		}
		//INSERT FOR MODULES
		public static void insertModules(Statement state, int id, String name, String category, String streamID){
		try{
		int r1=state.executeUpdate("INSERT INTO modules VALUES("+id+", '"+name+"', '"+category+"', '"+streamID+"')");
		System.out.println(r1+" rows inserted."); //Take this line out if you don't want a confirmation message
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		}
		//INSERT FOR COURSES
		public static void insertCourses(Statement state, String courseID, String name, int moduleID){
		try{
		int r1=state.executeUpdate("INSERT INTO courses VALUES('"+courseID+"', '"+name+"', "+moduleID+")");
		System.out.println(r1+" rows inserted."); //Take this line out if you don't want a confirmation message
		
		}
		catch (Exception e){
			e.printStackTrace();
		}
		}
		
}
	
	
	
	

