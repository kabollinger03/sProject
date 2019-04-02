package extraction.sProject;

public class Module {
	
	
	private String moduleID;
	private double moduelScore;
	
	
	
	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Module(String moduleID, double moduelScore) {
		super();
		this.moduleID = moduleID;
		this.moduelScore = moduelScore;
	}



	public String getModuleID() {
		return moduleID;
	}



	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}



	public double getModuelsScore() {
		return moduelsScore;
	}



	public void setModuelScore(double moduelsScore) {
		this.moduelScore = moduelsScore;
	}



	@Override
	public String toString() {
		return "Module [moduleID=" + moduleID + ", moduelScore=" + moduelScore + "]";
	}
	
	
	
	
	
}
