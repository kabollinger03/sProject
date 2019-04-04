
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author syntel
 */
public class main {

    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        manageUsers manageUsers = new manageUsers();
        manageUsers.init();
        
    }
    
}
