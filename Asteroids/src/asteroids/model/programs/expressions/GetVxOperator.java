package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;

public class GetVxOperator extends GetExpression {

	public GetVxOperator(Expression e) throws IllegalArgumentException {
		super(e);
	}

	@Override
	public Object getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getXVelocity();
	}

}
