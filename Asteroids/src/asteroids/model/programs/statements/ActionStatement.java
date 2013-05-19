package asteroids.model.programs.statements;

public abstract class ActionStatement extends Statement {

	protected ActionStatement(int line, int column){
		super(line,column);
	}
	
	@Override
	public abstract void execute();

}
