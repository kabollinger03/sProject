
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
    
    public static int generateModuleID(Statement stment) {
        String maxIDSql = "SELECT MAX(module_id) FROM modules";
        int newID = 1; // if no IDs are found, then IDs start at one
        
        try {
            ResultSet rs = stment.executeQuery(maxIDSql);
            
            if(rs.next()) // the max ID was retrieved
                newID = (rs.getInt(1) + 1); // increment the max ID to create a new one
            
        }catch(SQLException e) {
            e.printStackTrace(); 
        }finally {
            return newID;
        }
    }
}
