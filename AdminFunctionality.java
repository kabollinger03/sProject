import java.sql.Statement;
import java.util.Scanner;

public class AdminFunctionality 
{
	public static void createModule(Scanner userIn, Statement st)
	{
		
		System.out.println("Enter the module id: ");
        int id = userIn.nextInt();
        
        System.out.println("Enter the module name: ");
        String name = userIn.next();
        
        System.out.println("Enter the module category: ");
        String modCategory = userIn.next();
        
        //System.out.println("Enter the module stream id: ");
        String stream = "";
        
		try{
			int r1=st.executeUpdate("INSERT INTO modules VALUES("+id+", '"+name+"', '"+modCategory+"', '"+stream+"')");
			System.out.println(r1+" rows inserted.\n"); //Take this line out if you don't want a confirmation message
			
			}
			catch (Exception e){
				e.printStackTrace();
			}
	}
	
	
	public static void deleteModule(Scanner userIn, Statement st)
	{
		
		System.out.println("Enter the module id: ");
        int id = userIn.nextInt();
        
        try {

            String query = "DELETE FROM Student_Performance.modules where module_id= '" + id + "'";
            st.executeQuery(query);
            System.out.println("Module Deleted\n");


        } catch (Exception ex) {
             System.out.println(ex);
             System.out.println("Deleting User Failed");
        }
	}
	public static void updateModule(Scanner userIn, Statement st)
	{

        
		System.out.println("What would you like to update?");
		System.out.println("0. To go back");
		System.out.println("1. Update Name");
		System.out.println("2. Update Category");
        int od = userIn.nextInt();
        
        switch(od) {
	        case 0:
	        {
	        	break;
	        }
	        
	        case 1:
	        {
	        	System.out.println("Enter the module id: ");
	            int id = userIn.nextInt();
	            
	        	System.out.println("Enter new name: ");
	        	String new_name = userIn.next();
	        	try {
	        		
	        		String query = "Update Student_Performance.modules SET module_name = '" + new_name + "' where module_id= '" + id + "'";
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
	        	System.out.println("Enter the module id: ");
	            int id = userIn.nextInt();
	            
	        	System.out.println("Enter new category: ");
	        	String cat = userIn.next();
	        	try {
	        		
	        		String query = "Update Student_Performance.modules SET category = '" + cat + "' where module_id= " + id;
	                st.executeQuery(query);

	                System.out.println("Module Updated\n");


	            } catch (Exception ex) {
	                 System.out.println(ex);
	                 System.out.println("Updating User Failed");
	            }
	        	break;
	        }
        }
  
	}
	

}
