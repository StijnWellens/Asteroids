package asteroids.model.programs.statements;

public class Skip extends ActionStatement {
	
	public Skip(int line, int column){
		super(line,column);
	}

	@Override
	public void execute() {
		this.setFinished(true);
		
	}
}
