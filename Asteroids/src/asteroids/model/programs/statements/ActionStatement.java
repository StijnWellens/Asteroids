package asteroids.model.programs.statements;

import asteroids.model.programs.*;

public abstract class ActionStatement extends StandardStatement {

	protected ActionStatement(int line, int column, ProgramController controller){
		super(line,column);
		this.controller = controller;
	}
	
	private ProgramController controller;
	
	protected ProgramController getController(){
		return this.controller;
	}	
	
	@Override
	public boolean containsActionStatement() {
		return true;
	}
}
