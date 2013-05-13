package asteroids.model.programs.expressions;

public class Cosine extends MathSingleExpression {

	public Cosine(Expression e) throws IllegalArgumentException {
		super(e);
	}

	@Override
	public Object getValue() {
		return Math.cos((Double)this.getPart().getValue());
	}

}
