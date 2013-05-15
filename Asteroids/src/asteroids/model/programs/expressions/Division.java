package asteroids.model.programs.expressions;

import asteroids.Util;

public class Division extends MathBinaryExpression {

	public Division(int line, int column, Expression left, Expression right)
			throws IllegalArgumentException {
		super(line, column, left, right);
		if(Util.fuzzyEquals((Double)right.getValue(),0))
			throw new IllegalArgumentException();
	}

	@Override
	public Object getValue() {
		return (Double)(this.getLeftPart().getValue()) / (Double)(this.getRightPart().getValue());
	}

}
