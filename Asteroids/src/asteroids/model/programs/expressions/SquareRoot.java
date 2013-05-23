package asteroids.model.programs.expressions;

import asteroids.Util;
import asteroids.model.programs.exceptions.IllegalProgramException;

public class SquareRoot extends MathSingleExpression {

	public SquareRoot(int line, int column, Expression e) {
		super(line, column, e);
	}

	@Override
	public Double getValue() {
		if(Util.fuzzyLessThanOrEqualTo((Double)this.getPart().getValue(),0) && !Util.fuzzyEquals((Double)this.getPart().getValue(),0) )
			throw new IllegalProgramException("Square root of negative value");
		return Math.sqrt((Double)this.getPart().getValue());
	}
	
	@Override
	public String getSymbol() {
		return "sqrt";
	}

}
