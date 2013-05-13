package asteroids.model.programs.expressions;

public class Multiplication extends MathBinaryExpression {

	public Multiplication(Expression left, Expression right)
			throws IllegalArgumentException {
		super(left, right);
	}

	@Override
	public Object getValue() {
		return (Double)(this.getLeftPart().getValue()) * (Double)(this.getRightPart().getValue());
	}

}
