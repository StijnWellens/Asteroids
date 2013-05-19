package asteroids.model.programs.statements;


public abstract class Statement {

	protected Statement(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	public abstract void execute();
	
	private int line;
	private int column;
	
	public int getLine() {
		return this.line;
	}
	
	public int getColumn() {
		return this.column;
	}
}
