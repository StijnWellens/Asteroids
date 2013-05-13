package asteroids.model.programs.expressions;

public class Negation extends SingleExpression {

	public Negation(Expression e) throws IllegalArgumentException {
		super(e);
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
