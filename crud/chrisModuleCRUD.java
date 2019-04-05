
package sProject.crud;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class chrisModuleCRUD {
        
    public static ArrayList<String> getModuleNamesForStream(Statement stment, String ID) {
        
        ArrayList<String> moduleNames = new ArrayList<String>();
        String SQL = "SELECT module_name FROM modules WHERE stream_id = '" +
                    ID + "'";
        
        try {
            System.out.println(SQL);
            
            ResultSet rs = stment.executeQuery(SQL);
            
            while(rs.next())
                moduleNames.add(rs.getString("module_name"));
  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return moduleNames;
    } 
}
