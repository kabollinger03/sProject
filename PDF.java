//You will need apache pdfbox, apache fontbox, apache commonsloggings, jdbc JARs
//So far this will just generate a pdf with no values, still need to pull info from DB

import java.sql.*;
import java.io.IOException;
import org.apache.commons.logging.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDF {
	private PDDocument document = new PDDocument();
	private PDFont font = PDType1Font.TIMES_ROMAN;
	private PDPage page = new PDPage();
	private ResultSet rs;
	private Connection con;
	private final String conURL; //URL for connecting to DB
	private String conUSER;//Username for connecting to DB
	private String conPW;//Password for connecting to DB
	private String filePath;//Path to save the pdf
	
	public PDF(String conURL, String conUSER, String conPW, String filePath) {
		super();
		this.conURL = conURL;
		this.conUSER=conUSER;
		this.conPW=conPW;
		this.filePath = filePath;
	}
	
	public void generate(String empid) throws SQLException, IOException{ //empid not implemented yet, should be able to get everything you will need from it
		con = DriverManager.getConnection(conURL, conUSER, conPW);
		PreparedStatement psmt = con.prepareStatement("select * from tab"); //Placeholder
		ResultSet rs =   psmt.executeQuery();
		  while(rs.next())
            System.out.println(rs.getString(1));
		document = new PDDocument();
		font = PDType1Font.TIMES_ROMAN;
		page = new PDPage();
		document.addPage( page );
		
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		
		contentStream.setLeading(14.5f);
		contentStream.beginText();
		contentStream.setFont( font, 12 );
		contentStream.newLineAtOffset(100, 700);
		contentStream.showText( "Student ID: " );
		contentStream.newLine();
		contentStream.showText("Student email: ");
		contentStream.newLine();
		contentStream.showText("First Name: ");
		contentStream.newLine();
		contentStream.showText("Last Name: ");
		contentStream.newLine();
		contentStream.showText("Module X: "+ "     Grade: ");
		contentStream.endText();
		
		contentStream.close();
		document.save(filePath);
		document.close();
		rs.close();
		psmt.close();
		con.close();
	}
	
	public PDDocument getDocument() {
		return document;
	}
	public void setDocument(PDDocument document) {
		this.document = document;
	}
	public PDFont getFont() {
		return font;
	}
	public void setFont(PDFont font) {
		this.font = font;
	}
	public PDPage getPage() {
		return page;
	}
	public void setPage(PDPage page) {
		this.page = page;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public String getConUSER() {
		return conUSER;
	}
	public void setConUSER(String conUSER) {
		this.conUSER = conUSER;
	}
	public String getConPW() {
		return conPW;
	}
	public void setConPW(String conPW) {
		this.conPW = conPW;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getConURL() {
		return conURL;
	}

	@Override
	public String toString() {
		return "PDF [document=" + document + ", font=" + font + ", page=" + page + ", rs=" + rs + ", con=" + con
				+ ", conURL=" + conURL + ", conUSER=" + conUSER + ", conPW=" + conPW + ", filePath=" + filePath + "]";
	}

	
	
	
	
	
	
	
}
