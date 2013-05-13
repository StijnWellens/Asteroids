package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;

public class GetRadiusOperator extends GetExpression {

	public GetRadiusOperator(Expression e) throws IllegalArgumentException {
		super(e);
	}

	@Override
	public Object getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getRadius();
	}

}
