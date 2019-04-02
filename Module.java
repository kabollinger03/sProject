package extraction.sProject;

public class Module {
	
	private String moduleID;
	private double moduleScore;
	
	public Module() {
		super();
<<<<<<< HEAD
		this.moduleID = "N/A";
		this.moduleScore = 0;
	}



	public Module(String moduleID, double moduleScore) {
=======
	}
	public Module(String moduleID, double moduelsScore) {
>>>>>>> c251927f12032f7b079cb0dbc53cb2744907d313
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
<<<<<<< HEAD



	public void setModuelScore(double moduleScore) {
		this.moduleScore = moduleScore;
=======
	public void setModuelsScore(double moduelsScore) {
		this.moduelsScore = moduelsScore;
>>>>>>> c251927f12032f7b079cb0dbc53cb2744907d313
	}

	@Override
	public String toString() {
<<<<<<< HEAD
		return "Module [moduleID=" + moduleID + ", moduleScore=" + moduleScore + "]";
	}
	
	
	
	
	
=======
		return "Module [moduleID=" + moduleID + ", moduelsScore=" + moduelsScore + "]";
	}	
>>>>>>> c251927f12032f7b079cb0dbc53cb2744907d313
}
