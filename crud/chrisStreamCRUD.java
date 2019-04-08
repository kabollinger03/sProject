//package sProject.crud;

import java.sql.SQLException;
import java.sql.Statement;


public class chrisStreamCRUD {
    
    public static boolean streamExists(Statement stment, String ID) {
        
        String SQL = "SELECT * FROM stream WHERE stream_id = '" + ID + "'";
        int numRows = 0;
        
        try {
            numRows = stment.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numRows > 0;
    }
}
