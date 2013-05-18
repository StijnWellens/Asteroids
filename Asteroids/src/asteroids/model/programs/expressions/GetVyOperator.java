package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;

public class GetVyOperator extends GetExpression {

	public GetVyOperator(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Double getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getYVelocity();
	}

	@Override
	public String getSymbol() {
		return "getvy";
	}
}
