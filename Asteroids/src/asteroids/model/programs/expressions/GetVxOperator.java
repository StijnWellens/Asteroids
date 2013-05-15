package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;

public class GetVxOperator extends GetExpression {

	public GetVxOperator(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Double getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getXVelocity();
	}

}
