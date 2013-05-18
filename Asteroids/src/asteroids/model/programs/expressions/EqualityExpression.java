package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public abstract class EqualityExpression extends BinaryExpression {

	protected EqualityExpression(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
	}

	@Override
	public Type getType() {
		return Type.BOOL;
	}
	
}
