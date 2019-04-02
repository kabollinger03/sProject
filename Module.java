package extraction.sProject;

public class Module {
	
	
	private String moduleID;
	private double moduleScore;
	
	
	
	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Module(String moduleID, double moduleScore) {
		super();
		this.moduleID = moduleID;
		this.moduleScore = moduleScore;
	}



	public String getModuleID() {
		return moduleID;
	}



	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}



	public double getModuelsScore() {
		return moduleScore;
	}



	public void setModuelScore(double moduleScore) {
		this.moduleScore = moduleScore;
	}



	@Override
	public String toString() {
		return "Module [moduleID=" + moduleID + ", moduleScore=" + moduleScore + "]";
	}
	
	
	
	
	
}
