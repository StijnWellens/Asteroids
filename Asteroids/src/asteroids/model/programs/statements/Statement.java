package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.*;


public abstract class Statement {

	private boolean isFinished;
	
	protected Statement(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	public abstract void execute();
	
	private int line;
	private int column;
	
	@Basic
	public int getLine() {
		return this.line;
	}
	
	@Basic
	public int getColumn() {
		return this.column;
	}
	
	@Basic
	public boolean isFinished(){
		return this.isFinished;
	}
	
	public void setFinished(boolean finished){
		this.isFinished = finished;
	}
}
