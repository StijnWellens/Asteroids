package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public abstract class GetExpression extends SingleExpression {

	protected GetExpression(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
		if(e.getType() != Type.ENTITY)
			throw new IllegalArgumentException();
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}

}
