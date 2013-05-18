package asteroids.model.programs.expressions;

public abstract class StandardExpression extends Expression {

	protected StandardExpression(int line, int column) {
		super(line, column);
	}

	public abstract boolean equals(Object other);
	
	@Override
	public boolean containsExpression(Expression expression) {
		return expression == this;
	}
	
}
