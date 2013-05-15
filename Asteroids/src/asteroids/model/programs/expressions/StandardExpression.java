package asteroids.model.programs.expressions;

public abstract class StandardExpression extends Expression {

	protected StandardExpression(int line, int column) {
		super(line, column);
	}

	@Override
	public boolean containsExpression(Expression expression) {
		return expression == this;
	}
	
}
