package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public abstract class LogicalExpression extends BinaryExpression {

	protected LogicalExpression(int line, int column, Expression left, Expression right) throws IllegalArgumentException {
		super(line, column, left,right);
		if(left.getType() == Type.BOOL && right.getType() == Type.BOOL)
			throw new IllegalArgumentException();
	}
	
	@Override
	public Type getType() {
		return Type.BOOL;
	}

	
}
