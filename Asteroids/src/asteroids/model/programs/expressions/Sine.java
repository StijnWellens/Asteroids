package asteroids.model.programs.expressions;

public class Sine extends MathSingleExpression {

	public Sine(Expression e) throws IllegalArgumentException {
		super(e);
	}

	@Override
	public Object getValue() {
		return Math.sin((Double)this.getPart().getValue());
	}

}
