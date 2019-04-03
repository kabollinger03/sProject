
import java.sql.Connection;
import java.sql.ResultSet;

public interface CrudDAO {
    //https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
    //It's faster if nothing else, and you can set params by doing a 
    //foreach loop over the string array.
    
    /* Inserts a row into the database over the provided connection
    * @param con - a Connection object
    * @param values - A string array containing the values for each column in the row
    */
    public int insertRow(Connection con, String[] values); //pretty sure the executeX have useful return values
    //insertRows(String[] rows) ??
    
    public ResultSet getRow(Connection con, String id);
    //get all rows and get some rows?
    
    public int updateRow(Connection con, String[] values);
    //updateRows(String[] rows) ??
    
    public ResultSet deleteRow(Connection con, String id);
    //no delete all rows - that's why we have a DBA. 
    //no delete select rows - should be few enough times we can get away with looping calls to this fun    
}