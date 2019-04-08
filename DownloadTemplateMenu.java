import java.util.ArrayList;
import java.util.Scanner;

//import sProject.crud.*;

public class DownloadTemplateMenu {
    
    public static void show() {       
        System.out.println("Download Template Page");
        System.out.print("Please enter a stream ID: ");
    }

    public static String getStreamID(Scanner scanner) {
        
        EmployeePerformanceDAO empPerfDAO = new EmployeePerformanceDAO();
        String streamID = "";
        
        do{
            streamID = scanner.nextLine();
        }while(!empPerfDAO.streamExists(streamID));

        return streamID;
    }
    
    // asks user for a stream ID until a correct one is inputted
    // then all of the module names belonging to this stream are fetched
    // the static column titles and module names are written to the first row of the template file
    public static void createTemplate(Scanner scanner) {
        EmployeePerformanceDAO empPerfDAO = new EmployeePerformanceDAO();
        ExcelWriter excelWriter = new ExcelWriter();
        
        DownloadTemplateMenu.show(); // prompt for stream ID
        String streamID = DownloadTemplateMenu.getStreamID(scanner);

        ArrayList<String> columnTitles = new ArrayList<String>();
        columnTitles.add("Employee ID");
        columnTitles.add("Name"); // static columns
        columnTitles.add("Email");
        
        ArrayList<String> moduleNames = empPerfDAO.getModuleNamesForStream(streamID);

        columnTitles.addAll(moduleNames);
        
        excelWriter.createExcelFileWithTitles(columnTitles);
    }
    
}
