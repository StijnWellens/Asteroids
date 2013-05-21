package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public abstract class MathBinaryExpression extends BinaryExpression {

	
	protected MathBinaryExpression(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
		if(left.getType() != Type.DOUBLE || right.getType() != Type.DOUBLE)
			throw new IllegalArgumentException();
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}
	
}
