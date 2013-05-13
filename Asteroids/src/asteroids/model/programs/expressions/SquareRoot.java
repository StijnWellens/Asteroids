package asteroids.model.programs.expressions;

import asteroids.Util;

public class SquareRoot extends MathSingleExpression {

	public SquareRoot(Expression e) throws IllegalArgumentException {
		super(e);
		if(Util.fuzzyLessThanOrEqualTo((Double)e.getValue(),0) && !Util.fuzzyEquals((Double)e.getValue(),0) )
			throw new IllegalArgumentException();
	}

	@Override
	public Object getValue() {
		return Math.sqrt((Double)this.getPart().getValue());
	}

}
