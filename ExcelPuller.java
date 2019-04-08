import java.util.*;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
public class ExcelPuller {
	
            public ArrayList<Employee> generateEmployees(String FILE_PATH) throws IOException{
      
            	Workbook workbook = null;
            	ArrayList<String> columns = new ArrayList<>(); // the column titles: "name", "email", "empID", "mod1Score"
            	ArrayList<Employee> emps = new ArrayList<>();
                String classID = generateClassID();
            	
                try {

                    FileInputStream excelFile = new FileInputStream(new File(FILE_PATH));
                    workbook = new XSSFWorkbook(excelFile);
                    Sheet datatypeSheet = workbook.getSheetAt(0);
                    Iterator<Row> iterator = datatypeSheet.iterator(); // to traverse the rows
                   
                    Row currentRow = iterator.next();
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    
                    while (cellIterator.hasNext() ) {
                    	Cell currentCell = cellIterator.next();
                    	if (currentCell.getCellType() == CellType.STRING) {
                            
                    		columns.add(currentCell.getStringCellValue());
                    		
                         
                    	
                    	} else if (currentCell.getCellType() == CellType.NUMERIC) {
                           
                    		
                    		columns.add(currentCell.getStringCellValue());
                    	
                    	}
                    }
                    
                    
                    // go through every row after the first row
                    // create employee and module classes from these rows
                    
                    int counter;
                    while (iterator.hasNext()) {
                    	counter = 0;
                      currentRow = iterator.next(); // contains employee info and module scores
                      cellIterator = currentRow.iterator();
                      
                     
                      Employee newEmp = new Employee();
                        while (cellIterator.hasNext()) {                           
                        	
                        	Cell currentCell = cellIterator.next();  // empid, name, email, classId, m1Score, m2Score
             
                        	
                        	if (counter == 0) {
                        		newEmp.setEmployeeID(currentCell.getStringCellValue());//Gets Emp ID
                        		counter++;
                        	} else if(counter == 1){
                        		newEmp.setEmployeeName(currentCell.getStringCellValue());//Gets Emp Name
                        		counter++;
                        	} else if(counter == 2) {
                        		newEmp.setEmployeeEmail(currentCell.getStringCellValue());//Gets Emp Email
                        		counter++;
                        	} else if (counter >= 3){
                        		newEmp.addScore(columns.get(counter), currentCell.getNumericCellValue()); //Adds scores to an employee
                        		counter++;
                        		
                        	}else {
                        		System.out.println("All data entered");
                        	}
                        	            
                        	
                        	
                        	
                        	}
                        newEmp.setClassID(classID);
                        emps.add(newEmp);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                	
                	workbook.close();
                }
               return emps; 
            }
            
            public String generateClassID(){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyyHHmm");  
                LocalDateTime now = LocalDateTime.now();
                Scanner keyboard = new Scanner(System.in);
                String classID = "";
                String location;
                String site;
                String stream;
                StringBuilder s = new StringBuilder();
                
                
                System.out.println("Please enter your Stream Title");
                stream = keyboard.nextLine();
                s.append(stream.toUpperCase().charAt(0));
                s.append(stream.toUpperCase().charAt(1));
                s.append(stream.toUpperCase().charAt(2));
                s.append(dtf.format(now));
                System.out.println("Please enter your the class's location:");
                location = keyboard.nextLine();
                s.append(location.toUpperCase().charAt(0));
                s.append(location.toUpperCase().charAt(1));
                s.append(location.toUpperCase().charAt(2));
                
                System.out.println("Is the class onsite or offshore?");
                site = keyboard.nextLine();
                s.append(site.toUpperCase().charAt(0));
                s.append(site.toUpperCase().charAt(1));
                
                classID = s.toString();
                return classID;
            }
}

