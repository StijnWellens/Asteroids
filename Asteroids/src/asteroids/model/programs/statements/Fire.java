package asteroids.model.programs.statements;

import asteroids.model.programs.*;

public class Fire extends ActionStatement {
	
	public Fire(int line, int column, ProgramController controller){
		super(line,column,controller);
	}

	@Override
	public void execute() {
		this.getController().getProgram().getShipRunningProgram().fireBullet();
		this.setFinished(true);
	}

	@Override
	public boolean typeCheck() {
		return true;
	}
}
