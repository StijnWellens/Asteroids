package asteroids.model.programs.expressions;

public abstract class MathBinaryExpression extends BinaryExpression {

	
	protected MathBinaryExpression(Expression left, Expression right)
			throws IllegalArgumentException {
		super(left, right);
		if(left.getType() == Type.DOUBLE && right.getType() == Type.DOUBLE)
			throw new IllegalArgumentException();
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}
	
}
