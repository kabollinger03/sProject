package extraction.sProject;
import java.util.*;
public class Employee {
	
	private String employeeID;
	private String employeeName;
	private String employeeEmail;
	private double classID;
	private List<Module> modScores = new ArrayList<>();	
	
	
	
	
	public Employee() {
		super();
		this.employeeName = "N/A";
		this.employeeEmail = "N/A";
		this.classID = 0;
		this.modScores = null;
	}
	public Employee(String employeeID, String employeeName, String employeeEmail, double classID) {
		super();
		this.employeeID = employeeID;
		this.employeeName = employeeName;
		this.employeeEmail = employeeEmail;
		this.classID = classID;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public List<Module> getModScores() {
		return modScores;
	}
	
	public void setModScores(List<Module> modScores) {
		this.modScores = modScores;
	}
	
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	
	public double getClassID() {
		return classID;
	}
	
	public void setClassID(double classID) {
		this.classID = classID;
	}
	
	public String getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	public void addScore(String modName, double sco) {
		 Module mod = new Module(modName, sco);
		 modScores.add(mod);
	}
	
	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", employeeName=" + employeeName + ", employeeEmail="
				+ employeeEmail + ", classID=" + classID + ", modScores=" + modScores + "]";
	}
	
	
	
	
	
	
}
