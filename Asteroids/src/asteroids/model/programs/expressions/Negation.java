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
	public Boolean getValue() {
		return !(Boolean)(this.getPart().getValue());
	}

	@Override
	public String getSymbol() {
		return "!";
	}
	
	@Override
	public boolean typeCheck() {
		return (super.typeCheck() && (this.getPart().getType().equals(Type.BOOL)));
	}
}
