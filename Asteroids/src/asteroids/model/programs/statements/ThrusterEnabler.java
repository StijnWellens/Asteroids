package asteroids.model.programs.statements;

import asteroids.model.programs.*;

public class ThrusterEnabler extends ActionStatement {
	
	public ThrusterEnabler(int line,int column, ProgramController controller){
		super(line,column, controller);
	}

	@Override
	public void execute() {
		this.getController().getProgram().getShipRunningProgram().getThruster().setEnabled(true);
		this.setFinished(true);
	}
	
	@Override
	public boolean typeCheck() {
		return true;
	}
}
