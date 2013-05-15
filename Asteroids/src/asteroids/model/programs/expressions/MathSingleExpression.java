package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public abstract class MathSingleExpression extends SingleExpression {

	protected MathSingleExpression(int line, int column, Expression e)
			throws IllegalArgumentException {
		super(line, column, e);
		if(e.getType() != Type.DOUBLE)
			throw new IllegalArgumentException();
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}

}
