package asteroids.model.programs.expressions;

public abstract class ComparisonExpression extends BinaryExpression {

	protected ComparisonExpression(Expression left, Expression right)
			throws IllegalArgumentException {
		super(left, right);
		if(left.getType() == Type.DOUBLE && right.getType() == Type.DOUBLE)
			throw new IllegalArgumentException();
	}

	@Override
	public Type getType() {
		return Type.BOOL;
	}
	
}
