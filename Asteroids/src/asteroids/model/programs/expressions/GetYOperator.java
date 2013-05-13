package asteroids.model.programs.expressions;

import asteroids.model.*;

public class GetYOperator extends GetExpression {

	protected GetYOperator(Expression e) throws IllegalArgumentException {
		super(e);
	}

	@Override
	public Object getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getY();
	}

}
