package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public abstract class MathSingleExpression extends SingleExpression {

	protected MathSingleExpression(int line, int column, Expression e)
			throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}
	
	@Override
	public String toString(){
		String result = getSymbol() + "(" + this.getPart().toString() + ")";
		return result;
	}
	
	@Override
	public boolean typeCheck() {
		return(super.typeCheck() && (this.getPart().getType().equals(Type.DOUBLE)));
	}

}
