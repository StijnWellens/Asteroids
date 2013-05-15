package asteroids.model.programs.expressions;

import asteroids.model.SpaceObject;

public class GetRadiusOperator extends GetExpression {

	public GetRadiusOperator(int line, int column,Expression e) throws IllegalArgumentException {
		super(line, column,e);
	}

	@Override
	public Double getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getRadius();
	}

}
