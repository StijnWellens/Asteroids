package asteroids.model.programs.expressions;

import asteroids.Util;

public class Division extends MathBinaryExpression {

	public Division(Expression left, Expression right)
			throws IllegalArgumentException {
		super(left, right);
		if(Util.fuzzyEquals((Double)right.getValue(),0))
			throw new IllegalArgumentException();
	}

	@Override
	public Object getValue() {
		return (Double)(this.getLeftPart().getValue()) / (Double)(this.getRightPart().getValue());
	}

}
