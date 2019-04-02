package extraction.sProject;
import java.util.*;
import java.io.*;
import java.util.Iterator;

import org.apache.commons.math3.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
public class ExcelPuller {
	
            
			public ArrayList<Employee> generateEmployees(String FILE_NAME) throws IOException{
      
            	Workbook workbook = null;
            	ArrayList<String> columns = new ArrayList<>(); // the column titles: "name", "email", "empID", "mod1Score"
            	ArrayList<Employee> emps = new ArrayList<>();
            	
                try {

                    FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
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
                    for(int i = 0; i < columns.size(); i++) {   
                        System.out.print(columns.get(i) + " ");
                    } 
                    
                    // go through every row after the first row
                    // create employee and module classes from these rows
                    
                    int counter;
                    while (iterator.hasNext()) {
                    	counter = 0;
                      currentRow = iterator.next(); // contains employee info and module scores
                      cellIterator = currentRow.iterator();
                      
                      System.out.println("\nrow values:");
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
                        	} else if(counter == 3) {
                        		newEmp.setClassID(currentCell.getNumericCellValue()); //Gets Class ID
                        		counter++;
                        	}else if (counter >= 4){
                        		newEmp.addScore(columns.get(counter), currentCell.getNumericCellValue()); //Adds scores to an employee
                        		counter++;
                        		
                        	}else {
                        		System.out.println("All data entered");
                        	}
                        	            
                        	
                        	
                        	
                        	}
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
   
        }

