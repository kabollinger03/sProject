package AdminPages;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class manageUsers {
    private String userId;
    private String password;
    private String isAdmin;
    private int choice;
    private Scanner input = new Scanner(System.in);
    
    public void userOptionsForUsers(Statement st){
        System.out.println("Please choose an option to manage users: ");
        System.out.println("1. Create a user");
        System.out.println("2. Update a user");
        System.out.println("3. Delete a user");
        System.out.println("4. Quit Managing Users");
        choice = input.nextInt();
        
        while(choice < 1 || choice > 5)
        {
            System.out.println("Please make a choice between 1 and 4");
            System.out.println("Please choose an option to manage users: ");
            System.out.println("1. Create a user");
            System.out.println("2. Update a user");
            System.out.println("3. Delete a user");
            System.out.println("4. Quit Managing Users");
            choice = input.nextInt();
        }

        if(choice == 1){
            System.out.println("To create a user please provide the information asked for below:");
            System.out.println("Please enter the user ID: ");
            userId = input.next();
            
            System.out.println("Please enter the password: ");
            password = input.next();
            
            System.out.println("Please enter Y or N to determine if they are an admin: ");
            isAdmin = input.next();
            
            createUser(st, userId, password, isAdmin);
        }
        else if(choice == 2){
        	            
            System.out.println("Please choose what you would like to update for the user: ");
            System.out.println("1. Update user name");
            System.out.println("2. Update the password");
            System.out.println("3. Update if admin or not");
            choice = input.nextInt();
            
            if(choice == 1)
            {
                System.out.println("Please enter the old user name: ");
                userId = input.next();
                
                System.out.println("Please enter the new user name: ");
                String newUser = input.next();
                
                updateUser(st, userId, newUser);
            }
            else if(choice == 2)
            {
            	
            	System.out.println("Please enter the user name: ");
                userId = input.next();
                
                System.out.println("Please enter the new password: ");
                String password = input.next();
                
                updateUser2(st, userId, password);
            }
            else if(choice == 3){
            	System.out.println("Please enter the user name: ");
                userId = input.next();
                
                System.out.println("Please enter Y or N to make then an Admin: ");
                isAdmin = input.next();
                
                updateUser3(st, userId, isAdmin);
            }             
                    
        }
        else if(choice == 3){
        	System.out.println("To delete a user please provide the information asked for below:");
            System.out.println("Please enter the user ID: ");
            userId = input.next();
            
            deleteUser(st, userId);
            
            
        }
        else if(choice == 4){
        	 return;
        }
    }
    
    
   
    
    public void createUser(Statement st, String userId, String password, String isAdmin){
        try{
            st.executeUpdate("INSERT INTO users VALUES('"+userId+"', '"+password+"', '"+isAdmin+"')");
            System.out.println("user inserted.\n"); //Take this line out if you don't want a confirmation message
        }
        catch (Exception ex){
        	System.out.println(ex);
			System.out.println("Creating User Failed");
        }
    }
         
    
    public void updateUser(Statement st, String userId ,String newUser){
        try {
                System.out.println("Update a user");
                st.executeUpdate("UPDATE Student_Performance.Users SET user_id ='" + newUser + "' WHERE user_id ='" + userId + "'");
                System.out.println("User updated\n");
        } catch (Exception ex) {
        	System.out.println(ex);
			System.out.println("Updating User Failed");
        }
    }
    
    public void updateUser2(Statement st, String userId ,String password){
        try {
                System.out.println("Update a user");
                st.executeUpdate("UPDATE Student_Performance.Users SET password ='" + password + "' WHERE user_id ='" + userId + "'");
                System.out.println("User updated\n");
        } catch (Exception ex) {
        	System.out.println(ex);
			System.out.println("Updating User Failed");
        }
    }
    
    public void updateUser3(Statement st, String userId ,String isAdmin){
        try {
                System.out.println("Update a user");
                st.executeUpdate("UPDATE Student_Performance.Users SET isadmin ='" + isAdmin + "' WHERE user_id ='" + userId + "'");
                System.out.println("user updated\n");
        } catch (Exception ex) {
        	System.out.println(ex);
			System.out.println("Updating User Failed");
        }
    }
    
    
    public void deleteUser(Statement st, String userId){
        try {
                System.out.println("Delete User");
                st.executeUpdate("DELETE FROM Student_Performance.Users WHERE user_id = '" + userId + "'");
                System.out.println("User deleted\n");
        } catch (Exception ex) {
        	System.out.println(ex);
			System.out.println("Deleting User Failed");
        }
    }
    
    
    
    
    
}
