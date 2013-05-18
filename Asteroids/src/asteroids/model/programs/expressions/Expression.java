package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public abstract class Expression {

	protected Expression(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	public abstract boolean containsExpression(Expression expression);
	
	public abstract Type getType();
	
	public abstract Object getValue();
	
	private int line;
	private int column;
	
	public int getLine() {
		return this.line;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	@Override
	public abstract boolean equals(Object other);
	
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract String toString();	
	
	
}
