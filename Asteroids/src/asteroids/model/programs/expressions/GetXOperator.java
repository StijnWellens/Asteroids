package asteroids.model.programs.expressions;

import asteroids.model.*;

public class GetXOperator extends GetExpression {

	public GetXOperator(Expression e) throws IllegalArgumentException {
		super(e);
	}

	@Override
	public Object getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getX();
	}

}
