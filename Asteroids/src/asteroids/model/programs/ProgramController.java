package asteroids.model.programs;

import be.kuleuven.cs.som.annotate.*;
import asteroids.model.*;

/**
 * 
 * @author Stijn Wellens
 *
 * @invar	...
 * 			| hasProperProgram()	
 */
public class ProgramController {

	@Basic
	public boolean isInitialized() {
		return this.isInitialized;
	}
	
	private boolean isInitialized;
	
	@Basic
	public Program getProgram() {
		return this.program;
	}
	
	public boolean isValidProgram(Program program) {
		if(program == null)
			return false;
		return program.getController().equals(this);
	}
	
	public boolean hasProperProgram() {
		if(!isValidProgram(this.getProgram()))
			return false;
		return true;
	}
	
	private Program program;
		
	public void initializeProgramController(Program program) {
		if(isValidProgram(program)) {
			this.program = program;
			this.isInitialized = true;
		}
		else {
			this.isInitialized = false;
		}
	}
	
	
	
}
