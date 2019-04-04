package extraction.sProject;

import java.sql.ResultSet;
import java.sql.Statement;

public class ModuleCRUD {
	public void insertModule(Statement st, String module_id, String module_name, String category, String stream_id){
		try {
			System.out.println("Inserting a module...");
			int rows = st.executeUpdate("INSERT INTO Student_Performance.Modules VALUES ('" + module_id + "','" + module_name + "','" + category + "," + stream_id + "')");
			System.out.println(rows + " module added...");
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
	}
	
	public void readModule(Statement st, String module_id){
		try {
			System.out.println("Reading module...");
			ResultSet rs = st.executeQuery("SELECT * FROM Student_Performance.Modules WHERE module_id = '" + module_id + "'");
			while(rs.next())
				System.out.println("Result: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
	}
	
	public void updateModule(Statement st, String module_id, String stream_id, String module_name, String category){
		try {
			System.out.println("Update module.");
			int rows = st.executeUpdate("UPDATE Student_Performance.Modules SET stream_id ='" + stream_id + "', module_name='" + module_name + "'WHERE module_id ='" + module_id + "'");
			System.out.println(rows + " module updated");
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
	}
	
	public void deleteModule(Statement st, String module_id){
		try {
			System.out.println("Delete module.");
			int rows = st.executeUpdate("DELETE FROM Student_Performance.Module WHERE module_id = '" + module_id + "'");
			System.out.println(rows + " module deleted");
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}
	}
}
