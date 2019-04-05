package sProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    public void createExcelFileWithTitles(ArrayList<String> columnTitles) {

        XSSFWorkbook workbook = new XSSFWorkbook(); // blank workbook

        // blank spreadsheet
        XSSFSheet spreadsheet = workbook.createSheet("Performance Reports Template");

        XSSFRow row; // row object
        int rowIndex = 0; // the current row index, advanced as rows are created

        row = spreadsheet.createRow(rowIndex++); // the title row to indicate the data to be entered

        int columnIndex = 0;
        // go through titles, write their values in consecutive cells
        for(String colTitle: columnTitles) {
                Cell cell = row.createCell(columnIndex++);
                cell.setCellValue(colTitle);
        }

        FileOutputStream out;
        try {
            out = new FileOutputStream(new File("./template.xlsx"));
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       
        System.out.println("Template spreadsheet was written successfully");
    }
    
    public static void main(String[] args) {
        ExcelWriter excelWriter = new ExcelWriter();
        ArrayList<String> mockTitles = new ArrayList<String>();
        mockTitles.add("Name");
        mockTitles.add("Mod Score");
        
        excelWriter.createExcelFileWithTitles(mockTitles);
    }
}