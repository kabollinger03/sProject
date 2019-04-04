package extraction.sProject;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author syntel
 */
public class Runner {
    public static void ExcelUpload(String FILE_PATH) throws IOException {
        
        ExcelPuller pul = new ExcelPuller();
        ArrayList<Employee> emps = new ArrayList<>();
        EmployeeCRUD empCrud = new EmployeeCRUD();
        ClassCRUD cCrud = new ClassCRUD();
        ETMCrud eCrud= new ETMCrud();
        emps = pul.generateEmployees(FILE_PATH);
        
        
        
        try {
        	
        	Class.forName("oracle.jdbc.driver.OracleDriver");
        	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
        	Statement st = con.createStatement();
        	cCrud.insertClass(st, emps.get(0).getClassID(), "", "");
        	for(Employee x: emps) {
            	
            	empCrud.insertEmployee(st, x.getEmployeeID(), x.getEmployeeName(), x.getEmployeeEmail(), x.getClassID());
            	for(Module z: x.getModScores()) {
            	eCrud.insertETM(st, z.getModuleID(), z.getmoduleScore(), x.getEmployeeID());
            	}
        	}
        }catch(ClassNotFoundException e){
        	e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
    }
}
