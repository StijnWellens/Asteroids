package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.*;
import asteroids.model.programs.ProgramController;
import asteroids.model.programs.Type;
import asteroids.model.programs.expressions.*;

public class Turn extends ActionStatement {

	private Expression angle;
	
	public Turn(int line, int column, ProgramController controller, Expression angle)throws IllegalArgumentException{
		super(line,column, controller);
		this.angle = angle;
	}
	
	@Basic
	public Expression getAngle(){
		return this.angle;
	}

	@Override
	public void execute() {
		if(getAngle().getValue() != null && getAngle().getType() != null && this.getAngle().getType().equals(Type.DOUBLE)) {
			Double turn = (Double)(angle.getValue());
			this.getController().getProgram().getShipRunningProgram().turn(turn);
		}
		this.setFinished(true);
	}
	
	@Override
	public boolean typeCheck() {
		return (this.getAngle().typeCheck() && this.getAngle().getType().equals(Type.DOUBLE));
	}
}
