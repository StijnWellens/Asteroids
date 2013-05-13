package asteroids.model.programs.expressions;

public abstract class Expression {

	public abstract boolean containsExpression(Expression expression);
	
	public abstract Type getType();
	
	public abstract Object getValue();
}
