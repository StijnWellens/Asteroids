package asteroids.model.programs.expressions;

import asteroids.model.programs.Type;

public class Negation extends SingleExpression {

	public Negation(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
		if(e.getType() != Type.BOOL)
			throw new IllegalArgumentException();
	}

	@Override
	public Type getType() {
		return Type.BOOL;
	}

	@Override
	public Object getValue() {
		return !(Boolean)(this.getPart().getValue());
	}

}
