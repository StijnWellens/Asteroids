package asteroids.model.programs.expressions;

import asteroids.model.*;
import asteroids.model.programs.*;

public class Self extends StandardExpression {

	protected Self(int line, int column, ProgramController controller) {
		super(line, column);
		this.controller = controller;
	}

	private ProgramController controller;
	
	@Override
	public Type getType() {
		return Type.ENTITY;
	}

	@Override
	public SpaceObject getValue() {
		return controller.getProgram().getShipRunningProgram();
	}
	
	@Override
	public String toString(){
		return "self";
	}
}
