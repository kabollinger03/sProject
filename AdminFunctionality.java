package AdminPages;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminFunctionality 
{
	public static Scanner userIn = new Scanner(System.in).useDelimiter("\\n");
	public static Scanner user = new Scanner(System.in);
	
	public static void createModule(Statement st)
	{
				
		
	    int id = 1; // if no IDs are found, then IDs start at one
        
        System.out.println("Enter the module name: ");
        String name = userIn.nextLine();
        
        System.out.println("Enter the module category: ");
        String modCategory = userIn.nextLine();
        
        //System.out.println("Enter the module stream id: ");
        String stream = "";
        
		try{
			String maxIDSql = "SELECT MAX(module_id) FROM modules";
			ResultSet rs = st.executeQuery(maxIDSql);
			if(rs.next()) // the max ID was retrieved
			{
                id = (rs.getInt(1) + 1); // increment the max ID to create a new one
			}
			
			int r1=st.executeUpdate("INSERT INTO modules VALUES("+id+", '"+name+"', '"+modCategory+"', '"+stream+"')");
			System.out.println(r1+" rows inserted.\n"); //Take this line out if you don't want a confirmation message
			
			}
			catch (Exception e){
				e.printStackTrace();
			}
	}
	
	
	public static void deleteModule(Statement st)
	{
		
			System.out.println("Enter module name: ");
        	String name = userIn.next();
        
        try {

            String query = "DELETE FROM Student_Performance.modules where module_name= '" + name + "'";
            st.executeQuery(query);
            System.out.println("Module Deleted\n");


        } catch (Exception ex) {
             System.out.println(ex);
             System.out.println("Deleting User Failed");
        }
	}
	
	public static void updateModule(Statement st)
	{
		        
		System.out.println("What would you like to update?");
		System.out.println("0. To go back");
		System.out.println("1. Update Name");
		System.out.println("2. Update Category");
        int od = user.nextInt();
        
        switch(od) {
	        case 0:
	        {
	        	break;
	        }
	        
	        case 1:
	        {
	        
	        	System.out.println("Enter name of module: ");
	        	String name = userIn.next();
	            
	        	System.out.println("Enter new name: ");
	        	String new_name = userIn.next();
	        	try {
	        		
	        		String query = "Update Student_Performance.modules SET module_name = '" + new_name + "' where module_name= '" + name + "'";
	                st.executeQuery(query);

	                System.out.println("Module Updated\n");


	            } catch (Exception ex) {
	                 System.out.println(ex);
	                 System.out.println("Updating User Failed");
	            }
	        	break;
	        }
	        case 2:
	        {
	        	
	        	System.out.println("Enter module name: ");
	        	String name = userIn.next();
	        	
	        	System.out.println("Enter new category: ");
	        	String cat = userIn.next();
	        	try {
	        		
	        		String query = "Update Student_Performance.modules SET category = '" + cat + "' where module_name= " + "'" +name + "'";
	                st.executeQuery(query);
	                System.out.println("Module Updated\n");


	            } catch (Exception ex) {
	                 System.out.println(ex);
	                 System.out.println("Updating User Failed");
	            }
	        	break;
	        }
	        default: System.out.println("No such function, please choose again.");
			break;

        }
  
	}
	

}
