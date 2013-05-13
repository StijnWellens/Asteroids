package asteroids.model.programs.expressions;

public abstract class StandardExpression extends Expression {

	@Override
	public boolean containsExpression(Expression expression) {
		return expression == this;
	}
	
}
