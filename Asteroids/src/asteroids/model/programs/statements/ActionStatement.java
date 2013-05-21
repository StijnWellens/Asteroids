package asteroids.model.programs.statements;

public abstract class ActionStatement extends StandardStatement {

	protected ActionStatement(int line, int column){
		super(line,column);
	}
	
	@Override
	public abstract void execute();

}
