package asteroids.model.programs.statements;

import asteroids.model.programs.*;

public class ThrusterDisabler extends ActionStatement {
	
	public ThrusterDisabler(int line, int column, ProgramController controller){
		super(line,column, controller);
	}

	@Override
	public void execute() {
		this.getController().getProgram().getShipRunningProgram().getThruster().setEnabled(false);
		this.setFinished(true);
	}
}
