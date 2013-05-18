package asteroids.model.programs.expressions;

import asteroids.model.*;

public class GetYOperator extends GetExpression {

	public GetYOperator(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Double getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getY();
	}

	@Override
	public String getSymbol() {
		return "gety";
	}
}
