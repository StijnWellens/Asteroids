package asteroids.model.programs.expressions;

import asteroids.Util;
import asteroids.model.programs.exceptions.IllegalProgramException;

public class Division extends MathBinaryExpression {

	public Division(int line, int column, Expression left, Expression right){
		super(line, column, left, right);
	}

	@Override
	public Double getValue() {
		if(Util.fuzzyEquals((Double)this.getRightPart().getValue(),0))
			throw new IllegalProgramException("Dividing by 0");
		return (Double)(this.getLeftPart().getValue()) / (Double)(this.getRightPart().getValue());
	}
	
	@Override
	public String getSymbol() {
		return "/";
	}

}
