package asteroids.model.programs.statements;

import be.kuleuven.cs.som.annotate.*;
import asteroids.model.programs.Type;
import asteroids.model.programs.expressions.*;

public class Turn extends ActionStatement {

	private Expression angle;
	
	public Turn(int line, int column, Expression angle)throws IllegalArgumentException{
		super(line,column);
		if(angle.getType() != Type.DOUBLE)
			throw new IllegalArgumentException();
		this.angle = angle;
	}
	
	@Basic
	public Expression getAngle(){
		return this.angle;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
