package asteroids.model.programs;

import asteroids.model.*;

public class ProgramController {

	public boolean isInitialized() {
		return this.isInitialized;
	}
	
	private boolean isInitialized;
	
	public Program getProgram() {
		return this.program;
	}
	
	public boolean isValidProgram(Program program) {
		if(program == null)
			return false;
		return program.getStatement().getProgramController().equals(this);
	}
	
	private Program program;
		
	public void initializeProgramController(Program program) {
		this.program = program;
		this.isInitialized = true;
	}
	
	
	
}
