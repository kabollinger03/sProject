package extraction.sProject;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Runner {
    
    public static ArrayList<Employee> ExcelUpload(String FILE_PATH) throws IOException {
        
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
                
                // PDF setup
                PDF pdfCreator = new PDF(con);
                
        	for(Employee x: emps) {
            	
                    empCrud.insertEmployee(st, x.getEmployeeID(), x.getEmployeeName(), x.getEmployeeEmail(), x.getClassID());
                    
                    pdfCreator.generate(x.getEmployeeID());
                    
                    for(Module z: x.getModScores()) {
                        eCrud.insertETM(st, z.getModuleID(), z.getmoduleScore(), x.getEmployeeID());
                    }
        	}
                con.close();
        } catch(ClassNotFoundException e){
        	e.printStackTrace();
        } catch (Exception e) {
	
		e.printStackTrace();
        }
  
      return emps;
    }
    
    public static void main(String[] args) {
        try {
            Runner.ExcelUpload("C:\\Chris\\Projects\\ReportsCreator\\template.xlsx");
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }
}
