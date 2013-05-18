package asteroids.model.programs.expressions;

import asteroids.model.*;

public class GetXOperator extends GetExpression {

	public GetXOperator(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Double getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getX();
	}

	@Override
	public String getSymbol() {
		return "getx";
	}
}
