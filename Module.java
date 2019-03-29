package extraction.sProject;

public class Module {
	
	
	private String moduleID;
	private double moduelsScore;
	
	
	
	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Module(String moduleID, double moduelsScore) {
		super();
		this.moduleID = moduleID;
		this.moduelsScore = moduelsScore;
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



	public void setModuelsScore(double moduelsScore) {
		this.moduelsScore = moduelsScore;
	}



	@Override
	public String toString() {
		return "Module [moduleID=" + moduleID + ", moduelsScore=" + moduelsScore + "]";
	}
	
	
	
	
	
}
