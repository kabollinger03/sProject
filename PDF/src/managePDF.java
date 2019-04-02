
import java.io.IOException;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author syntel
 */
public class managePDF {
    public static void main(String[] args) {
        PDF pdf = new PDF("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance","emp.pdf");
        try{
            pdf.generate("1234");
        }catch( SQLException e){
        }catch(IOException e){
        }
        
    }
}
