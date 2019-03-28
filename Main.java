/*
 * You will need: odbc, apache pdfbox, apache fontbox, apache common loggings as JAR
 * 
 */
import java.sql.*;
import java.io.IOException;
import org.apache.commons.logging.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		
		// Create a new empty document
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Student_Performance","Student_Performance");
		Statement st = con.createStatement();
		PreparedStatement pstmt = con.prepareStatement("select * from tab"); 
	
		ResultSet rs =   st.executeQuery("select * from employees");
		PDDocument document = new PDDocument();
		PDFont font = PDType1Font.TIMES_ROMAN;
		PDPage page = new PDPage();
		document.addPage( page );
		// Start a new content stream which will "hold" the to be created content
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		// Create a new blank page and add it to the document
		PDPage blankPage = new PDPage();
		contentStream.setLeading(14.5f);

		document.addPage( blankPage );
		contentStream.beginText();
		contentStream.setFont( font, 12 );
		contentStream.moveTextPositionByAmount( 100, 700 );
		//select studentid from students;
		contentStream.showText( "Student ID: " );
		contentStream.newLine();
		//select studentemail from students;
		contentStream.showText("Student email: ");
		contentStream.newLine();
		//select f_name from students;
		contentStream.showText("First Name: ");
		contentStream.newLine();
		//select l_name from students;
		contentStream.showText("Last Name: ");
		contentStream.newLine();
		//select s.grade, m.modname from stud_mod s, modules m where m.mod_id=s.mod_id;
		contentStream.showText("Module X: "+ "     Grade: ");
		contentStream.endText();
		st.close();
		rs.close();
		con.close();
		 contentStream.close();
		// Save the newly created document
		document.save("BlankPage1.pdf");

		// finally make sure that the document is properly
		// closed.
		document.close();

		// TODO Auto-generated method stub

	}

}
