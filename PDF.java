package extraction.sProject;
//You will need apache pdfbox, apache fontbox, apache commonsloggings, jdbc JARs
//So far this will just generate a pdf with no values, still need to pull info from DB

import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
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
       
        private Connection connection;
	
	public PDF(Connection connection) {
		super();
                
                this.connection = connection; // only this matters
                
                conURL = null;
                conUSER = null;
	}
	
	public void generate(String empid) throws SQLException, IOException, Exception{ //empid not implemented yet, should be able to get everything you will need from it
		ArrayList<String> stream = new ArrayList();
                String name;
                String id;
                String foundation;
                String specialization;
                String domain;
                String avgGrade;
                String fGrade;
                String sGrade;
                String dGrade;
                String induction;
                PDFinfo info = new PDFinfo(connection);
		document = new PDDocument();
		font = PDType1Font.TIMES_ROMAN;
		page = new PDPage();
		document.addPage( page );
		ArrayList <String>foundations = new ArrayList();
                ArrayList <String>specializations = new ArrayList();
                ArrayList <String>domains = new ArrayList();
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
                
                name=info.getEmployeeName(empid);
                stream=info.getStreamIDName(empid);
                avgGrade=info.getAverageScoresByEmployeeID(empid);
                foundations.addAll(info.getModScoreByFoundation(empid));
                specializations.addAll(info.getModScoreBySpecialization(empid));
                domains.addAll(info.getModScoreByProcessDomain(empid));
                fGrade=info.getAverageScoresByFoundationEmployeeID(empid);
                sGrade=info.getAverageScoresBySpecializationEmployeeID(empid);
                dGrade=info.getAverageScoresByProcessDomainEmployeeID(empid);
		contentStream.setLeading(14.5f);
		contentStream.beginText();
		contentStream.setFont( font, 76 );
		contentStream.newLineAtOffset(100, 700);
                contentStream.showText("LOGO HERE");
                contentStream.newLine();
                contentStream.setFont( font, 12);
		contentStream.showText( "Training Report: "+name+"            ");//NAME select name from employees where employee_id=empid?
                contentStream.showText("Emp ID: "+empid);//Just use empid param
		contentStream.newLine();
		contentStream.showText("Induction: "+stream.toString());//Stream Code + Stream Name select s.stream_id, s.stream_name from Stream s, Class c, Employees e where s.stream_id=c.stream_id and c.class_id=e.class_id and e.employee_id='ab'
		contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Foundation: ");//select distinct c.course_name from 
                contentStream.newLine();
                for(int i = 0; i < foundations.size(); i+=2){
                    contentStream.showText(foundations.get(i)+" "+foundations.get(i+1));
                    contentStream.newLine();
                }
                contentStream.newLine();
                contentStream.showText("Specialization: ");
                contentStream.newLine();
                  for(int i = 0; i < specializations.size(); i+=2){
                    contentStream.showText(specializations.get(i)+" "+specializations.get(i+1));
                    contentStream.newLine();
                }
                contentStream.newLine();
                contentStream.showText("Process/Domain: ");
                contentStream.newLine();
                  for(int i = 0; i < domains.size(); i+=2){
                    contentStream.showText(domains.get(i)+" "+domains.get(i+1));
                    contentStream.newLine();
                }
                contentStream.newLine();
                contentStream.showText("Overall Grade:  "+avgGrade+"   ");//select avg(e.scores) from Employees_take_Modules e, Modules m where m.module_id=e.module_id and e.employee_id=?
                contentStream.showText("Foundation Grade: "+fGrade+"   ");//select avg(e.scores) from Employees_take_Modules e, Modules m where m.category='Foundation' and m.module_id=e.module_id and e.employee_id=?
                contentStream.showText("Specialization Grade: "+sGrade+"   ");//select avg(e.scores) from Employees_take_Modules e, Modules m where m.category='Specialization' and m.module_id=e.module_id and e.employee_id=?
                contentStream.showText("Process/Domain Grade: "+dGrade+"   ");//select avg(e.scores) from Employees_take_Modules e, Modules m where m.category='ProcessDomain' and m.module_id=e.module_id and e.employee_id=?
		contentStream.endText();
		contentStream.close();
		document.save(empid+".pdf");
		document.close();
		
                
		
                //BarChartEx bc = new BarChartEx();
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
