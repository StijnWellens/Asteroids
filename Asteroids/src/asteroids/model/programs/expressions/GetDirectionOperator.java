package asteroids.model.programs.expressions;

import asteroids.model.*;

public class GetDirectionOperator extends GetExpression {

	public GetDirectionOperator(int line, int column, Expression e)
			throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Double getValue() {
		if((this.getPart().getValue()) instanceof Ship) {
			return ((Ship)(this.getPart().getValue())).getDirection();
		}
		return 0.;
	}
	
	@Override
	public String getSymbol() {
		return "getdir";
	}

}
