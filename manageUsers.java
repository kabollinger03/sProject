import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author syntel
 */
public class manageUsers {
    
    private Connection connection;
    private PreparedStatement stmt;
    private Scanner input;
    
    private String initChoice;
    
    
    // INITIALIZATION METHOD. CHOOSING WHETHER TO CREATE, UPDATE OR DELETE.
    public void init()
    {
        System.out.println("Would you like to create, update, or delete a user (CREATE, UPDATE, or DELETE)?: ");
        input = new Scanner(System.in);
        initChoice = input.nextLine().toUpperCase().trim();
        
        if (initChoice.equals("CREATE"))
        {
            createUser();
        }
        
        else if (initChoice.equals("UPDATE"))
        {
            updateUser();
        }
        
        
        else if (initChoice.equals("DELETE"))
        {
            deleteUser();
        }
        
        else
        {
            System.out.println("Please enter CREATE, UDPATE, or DELETE");
            init();
        }
    }
    
    // DELETE USER ON "Users" TABLE
    public void deleteUser()
    {
        // change print statement to appriopriate form of input
        input = new Scanner(System.in);
        String userIdDB = null;
        
        System.out.println("Please enter a User ID to delete: ");
        String userIdToDelete = input.nextLine().trim();
        
        try {
            System.out.println("One moment...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");                      
                
            stmt = connection.prepareStatement("select user_id from Users where user_id = ?");
            stmt.setString(1, userIdToDelete); 


            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                userIdDB = rs.getString("user_id");
            }
            
            if (userIdToDelete.equals(userIdDB))
            {
                stmt = connection.prepareStatement("delete from Users where user_id = ?");  
                stmt.setString(1, userIdToDelete);

                System.out.println("Deleting User...");
                stmt.executeUpdate();

                System.out.println("Deleted successfully. Have a nice day.");
            }
                        
            else
            {
                System.out.println("Sorry. This user does not exist.");
                deleteUser();
            }
            
            connection.close();
            stmt.close();
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    // UPDATE USER ON "Users" table
    public void updateUser()
    {
        input = new Scanner(System.in);
            
        System.out.println("Please enter a user ID to update: ");
        String userIdToUpdate = input.nextLine();
        
        if (checkIfUserExists(userIdToUpdate))
        {
            System.out.println("User exists");
        }
        else
        {
            System.out.println("Sorry. User does not exist.");
            updateUser();
        }  
        
        System.out.println("What is the new password?: ");
        String newPassword = input.nextLine();
                    
        System.out.println("Is User Admin? Please enter Y or N.");
        String isAdmin = input.nextLine().toUpperCase().trim();

        while(!(isAdmin.equals("Y") || isAdmin.equals("N")))
        {
            System.out.println("Please enter only the character 'Y' or 'N'.");
            isAdmin = input.nextLine().toUpperCase().trim();
        }
               
        try 
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
            stmt = connection.prepareStatement("update Users set password = ?, isAdmin = ? where user_id = ?");  
            stmt.setString(1, newPassword);
            stmt.setString(2, isAdmin);
            stmt.setString(3, userIdToUpdate);

            System.out.println("Updating User...");
            stmt.executeUpdate();
            connection.close();
            stmt.close();
                                    
            System.out.println("Update Complete. Have a nice day.");
                
        } catch (Exception e) {
            e.printStackTrace();
        }                    
        
    }
    
    
    // ADD A NEW USER TO THE "Users" TABLE
    public void createUser()
    {
        input = new Scanner(System.in);
                
        // "password" on Users table
        String passwordToAdd;
        
        // "isAdmin" on Users table
        String isAdminOrNot;
            
        System.out.println("Please enter the new User ID: ");
        
        // "user_id" on Users table
        String userIdToAdd = input.nextLine().toUpperCase().trim();
        
        
        // check for duplicate user ID
        // if the user ID exists, return error message.
        if(checkIfUserExists(userIdToAdd))
        {
            System.out.println("Sorry. This User ID is not available.");
            createUser();
        }
        
        // else, user ID is available to use.
        else
        {
            System.out.println("User ID is available.");
        }
        
                 
        System.out.println("Please enter the password: ");
        passwordToAdd = input.nextLine().trim(); // "password"
                
        System.out.println("Is this new user an Admin (Y or N)?: ");
        isAdminOrNot = input.nextLine().toUpperCase().trim(); // "isAdmin"
                
        // if input for admin is not Y or N 
        while(!(isAdminOrNot.equals("Y") || isAdminOrNot.equals("N")))
        {
            System.out.println("Please enter only the character 'Y' or 'N': ");
            isAdminOrNot = input.nextLine().toUpperCase().trim();
        }

                
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
            stmt = connection.prepareStatement("insert into Users (user_id, password, isAdmin) values (?,?,?)");
            stmt.setString(1, userIdToAdd); // "user_id"
            stmt.setString(2, passwordToAdd); // "password"
            stmt.setString(3, isAdminOrNot); // "isAdmin"

            System.out.println("Creating User...");
            stmt.executeUpdate();
                    
            connection.close();
            stmt.close();
            System.out.println("User created. Have a nice day.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
            
    }
    
    // CHECK FOR DUPLICATE USER
    public Boolean checkIfUserExists(String enteredUserId)
    {
        String userIdDB = null;
        try {
            System.out.println("One moment...");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");                      
                
            stmt = connection.prepareStatement("select user_id from Users where user_id = ?");
            stmt.setString(1, enteredUserId); 


            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                userIdDB = rs.getString("user_id");
            }
            
            // if user ID DOES exist in database
            if (!enteredUserId.equals(userIdDB))
            {
                connection.close();
                stmt.close();
                return false;
            }
            
            // if user ID DOES exists in database
            else
            {
                connection.close();
                stmt.close();
                return true;
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
