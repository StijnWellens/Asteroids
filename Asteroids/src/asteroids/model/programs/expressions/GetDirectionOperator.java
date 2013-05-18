package asteroids.model.programs.expressions;

import asteroids.model.programs.ProgramController;

public class GetDirectionOperator extends GetExpression {

	protected GetDirectionOperator(int line, int column, Expression e,ProgramController controller)
			throws IllegalArgumentException {
		super(line, column, e);
		this.controller = controller;
	}

	private ProgramController controller;
	
	@Override
	public Double getValue() {
		return controller.getProgram().getShipRunningProgram().getDirection();
	}
	
	@Override
	public String getSymbol() {
		return "getdir";
	}

}
