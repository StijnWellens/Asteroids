package asteroids.model.programs.expressions;

public class Sine extends MathSingleExpression {

	public Sine(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Object getValue() {
		return Math.sin((Double)this.getPart().getValue());
	}

}
