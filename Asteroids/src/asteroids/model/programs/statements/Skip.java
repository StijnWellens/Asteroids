package asteroids.model.programs.statements;

import asteroids.model.programs.*;

public class Skip extends ActionStatement {
	
	public Skip(int line, int column, ProgramController controller){
		super(line,column, controller);
	}

	@Override
	public void execute() {
		this.setFinished(true);
		
	}

	@Override
	public boolean typeCheck() {
		return true;
	}
}
