package asteroids.model.programs.expressions;

public abstract class GetExpression extends SingleExpression {

	protected GetExpression(Expression e) throws IllegalArgumentException {
		super(e);
		if(e.getType() != Type.ENTITY)
			throw new IllegalArgumentException();
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}

}
