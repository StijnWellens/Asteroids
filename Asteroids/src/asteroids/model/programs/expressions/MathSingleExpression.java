package asteroids.model.programs.expressions;

public abstract class MathSingleExpression extends SingleExpression {

	protected MathSingleExpression(Expression e)
			throws IllegalArgumentException {
		super(e);
		if(e.getType() != Type.DOUBLE)
			throw new IllegalArgumentException();
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}

}
