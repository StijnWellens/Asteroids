package asteroids.model.programs.expressions;

public class Cosine extends MathSingleExpression {

	public Cosine(int line, int column, Expression e) throws IllegalArgumentException {
		super(line, column, e);
	}

	@Override
	public Object getValue() {
		return Math.cos((Double)this.getPart().getValue());
	}

}
