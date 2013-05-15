package asteroids.model.programs.expressions;

import asteroids.model.*;

public class GetYOperator extends GetExpression {

	public GetYOperator(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Object getValue() {
		return ((SpaceObject)(this.getPart().getValue())).getY();
	}

}
