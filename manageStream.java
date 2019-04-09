
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class manageStream {
    private String streamName;
    private String streamId;
    private int choice;
    private Scanner userInput = new Scanner(System.in).useDelimiter("\\n");
    
    public void userOptionsForStream(Statement st){
        do{
            System.out.println("Please choose an option to manage a stream: ");
            System.out.println("1. Create a stream");
            System.out.println("2. Retrieve/Search a stream");
            System.out.println("3. Update a stream");
            System.out.println("4. Delete a stream");
            System.out.println("5. Quit Managing Streams");
            choice = userInput.nextInt();
        }while(choice < 1 || choice > 5);

        if(choice == 1){
            System.out.println("To create a stream please provide the information asked for below:");
                       
            do{
                System.out.println("Please enter the stream name with more than 3 characters: ");
                streamName = userInput.next();
            }while(streamName.length() < 3);            
            
            streamId = genStreamId(streamName);
            
            createStream(st, streamId, streamName);
        }
        else if(choice == 2){
            System.out.println("To retrieve or search for a stream please provide the information asked for below:");
            System.out.println("Please enter the stream name: ");
            streamName = userInput.next();
            
            readStream(st, streamName);
        }
        else if(choice == 3){
            System.out.println("To update the stream name please provide the information asked for below:");
            System.out.println("Please enter the old stream name: ");
            streamName = userInput.next();
            
            System.out.println("Please enter the new stream name: ");
            String streamNewName = userInput.next();
             
            updateStream(st, streamNewName, streamName);
        }
        else if(choice == 4){
            System.out.println("To delete a course please provide the information asked for below:");
            System.out.println("Please enter the stream name: ");
            streamName = userInput.next();
            
            deleteStream(st, streamName);
        }
        else if(choice == 5){
            return;
        }
    }
    
    
    public void createStream(Statement st, String streamId, String streamName){               
        try{
            st.executeUpdate("INSERT INTO Student_Performance.Stream VALUES('"+streamId+"', '"+streamName+"')");
            System.out.println("Stream inserted."); //Take this line out if you don't want a confirmation message
        }
        catch (Exception e){
                e.printStackTrace();
        }
    }
    
    public void readStream(Statement st, String streamName){        
        try {
                System.out.println("Retrieving stream");
                ResultSet rs = st.executeQuery("select stream_id, stream_name from Student_Performance.Stream WHERE stream_name='" + streamName + "'");
                while(rs.next())
                        System.out.println("Result: " + rs.getString(1) + " " + rs.getString(2));
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
        }
    }
    
    public void updateStream(Statement st, String streamNewName, String streamName){        
        try {
                System.out.println("Update a stream");
                st.executeUpdate("UPDATE Student_Performance.Stream SET stream_name ='" + streamNewName + "'WHERE stream_name ='" + streamName + "'");
                System.out.println("Stream updated");
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
        }
    }
    
    public void deleteStream(Statement st, String streamName){
        try {
                System.out.println("Delete stream");
                st.executeUpdate("DELETE FROM Student_Performance.Stream WHERE stream_name = '" + streamName + "'");
                System.out.println("Stream deleted");
        } catch (Exception e) {
                System.out.println("Exception " + e.getMessage());
        }
    }
    
    private String genStreamId(String streamName){
        String generatedID = null;

        generatedID = (streamName.toUpperCase().replaceAll("\\s+","").substring(0, 3) + setDate());
      
        return generatedID;
    }
    
     // Date Format to use for ID generation
    public String setDate(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MMddyy:HHMMss");
        String formattedDate = myDateObj.format(myFormatObj);

        return formattedDate;
    }    
}
