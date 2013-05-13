package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;

public class GetVyOperator extends GetExpression {

	public GetVyOperator(Expression e) throws IllegalArgumentException {
		super(e);
	}

	@Override
	public Object getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getYVelocity();
	}

}
