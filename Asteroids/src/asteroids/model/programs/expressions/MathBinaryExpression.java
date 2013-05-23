package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public abstract class MathBinaryExpression extends BinaryExpression {

	
	protected MathBinaryExpression(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}
	
	@Override
	public boolean typeCheck() {
		return(super.typeCheck() && this.getLeftPart().getType().equals(Type.DOUBLE));
	}
	
}
