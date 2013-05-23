package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public abstract class GetExpression extends SingleExpression {

	protected GetExpression(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Type getType() {
		return Type.DOUBLE;
	}
	
	@Override
	public boolean typeCheck() {
		return(super.typeCheck() && (this.getPart().getType().equals(Type.ENTITY)));
	}

}
